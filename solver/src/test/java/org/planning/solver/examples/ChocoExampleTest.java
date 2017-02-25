package org.planning.solver.examples;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import org.junit.Test;
import org.planning.solver.test.AbstractSolverTest;

/**
 * @author pascalstammer
 * @version 31.01.17.
 */
public class ChocoExampleTest extends AbstractSolverTest {

    private static final Logger log = LogManager.getLogger( ChocoExampleTest.class );

    @Test
    public void testFirstModel() {

        int numberOfLectures = 20;
        Model model = new Model( "Scheduling" );

        IntVar[][] vars = new IntVar[numberOfLectures][4];

        for ( int i = 0; i < numberOfLectures; i++ ) {
            vars[i][0] = model.intVar( "lecture_" + i + "_instructor", 0, 4 );
            vars[i][1] = model.intVar( "lecture_" + i + "_room", 0, 4 );
            vars[i][2] = model.intVar( "lecture_" + i + "_day", 0, 4 );
            vars[i][3] = model.intVar( "lecture_" + i + "_slot", 0, 2 );
        }

        // Lecture - Instructor Constraints
        for ( int i = 0; i < 5; i++ ) {
            model.member( vars[i][0], new int[]{0, 1} ).post();
            model.member( vars[i + 5][0], new int[]{2, 3} ).post();
            model.member( vars[i + 10][0], new int[]{1, 4} ).post();
            model.member( vars[i + 15][0], new int[]{2, 4} ).post();
        }

        // Lecture - Room Constraints
        for ( int i = 0; i < 10; i++ ) {
            model.member( vars[i][1], new int[]{0, 1, 3} ).post();
            model.member( vars[i + 10][1], new int[]{0, 2, 4} ).post();
        }


        for ( int i = 0; i < numberOfLectures; i++ ) {

            // Room - Day Constraints
            model.ifThen( vars[i][1].eq( 0 ).decompose(), vars[i][2].eq( 0 ).decompose());
            model.ifThen( vars[i][1].eq( 1 ).decompose(), model.member( vars[i][2], new int[]{0, 1, 2, 3}));
            model.ifThen( vars[i][1].eq( 2 ).decompose(), model.member( vars[i][2], new int[]{1, 2, 3, 4}));

            // Room - TimeSlot Constraints
            model.ifThen( vars[i][1].eq( 3 ).decompose(), vars[i][3].eq( 0 ).decompose());
            model.ifThen( vars[i][1].eq( 4 ).decompose(), vars[i][3].eq( 2 ).decompose());

            // Instructor - Day Constraints
            model.ifThen( vars[i][0].eq( 0 ).decompose(), model.member( vars[i][2], new int[]{1, 2, 3} ) );
            model.ifThen( vars[i][0].eq( 1 ).decompose(), model.member( vars[i][2], new int[]{0, 1, 2} ) );
            model.ifThen( vars[i][0].eq( 2 ).decompose(), model.member( vars[i][2], new int[]{3, 4} ) );

            // Instructor - TimeSlot Constraints
            model.ifThen( vars[i][0].eq( 3 ).decompose(), model.member( vars[i][3], new int[]{1, 2} ));
            model.ifThen( vars[i][0].eq( 4 ).decompose(), model.member( vars[i][3], new int[]{0, 1} ));

        }

        // No Two Lecture at the same time and room TODO is false
        for(int i = 0; i < numberOfLectures; i++) {
            for(int j = 0; j < numberOfLectures; j++) {
                if(i != j) {
                    model.not(model.and(
                            new Constraint[]{
                                    vars[i][0].eq( vars[j][0] ).decompose(),
                                    vars[i][1].eq( vars[j][1] ).decompose(),
                                    vars[i][2].eq( vars[j][2] ).decompose(),
                                    vars[i][3].eq( vars[j][3] ).decompose()
                            }
                    )).post();
                }
            }
        }


        final IntVar[] instructorVars = new IntVar[numberOfLectures];
        for(int i = 0; i < numberOfLectures; i++) {
            instructorVars[i] = vars[i][0];
        }

        IntVar limit = model.intVar("limit", 0, 5);
        for( int lecture = 0; lecture < numberOfLectures; lecture++) {
            for( int instructor = 0; instructor < 5; instructor++) {
                model.count( instructor, instructorVars, limit ).post();
            }

        }


        Solution solution = model.getSolver().findSolution();

        final String[] map = new String[]{"Instructor: ", "Room: ", "Day: ", "Slot: "};
        if ( solution != null ) {
            for(int i = 0; i < numberOfLectures; i++) {
                System.out.print( "Lecture "+ (i + 1) + ", " );
                for(int j = 0; j < 4; j++) {
                    System.out.print( map[j] + Integer.toString( vars[i][j].getValue() ));
                    if(j < 3) {
                        System.out.print( ", " );
                    }
                }
                System.out.println( "" );
            }
        }
    }
}
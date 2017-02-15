package org.planning.core.examples;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.chocosolver.sat.SatFactory;
import org.chocosolver.solver.ICause;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Propagator;
import org.chocosolver.solver.constraints.nary.cnf.LogOp;
import org.chocosolver.solver.exception.ContradictionException;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.search.strategy.decision.IntDecision;
import org.chocosolver.solver.search.strategy.decision.RealDecision;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.util.ESat;
import org.chocosolver.util.objects.setDataStructures.iterable.IntIterableBitSet;
import org.chocosolver.util.objects.setDataStructures.iterable.IntIterableSet;
import org.junit.Test;
import org.planning.core.test.AbstractCoreTest;

import java.util.List;

/**
 * @author pascalstammer
 * @version 31.01.17.
 */
public class ChocoExampleTest extends AbstractCoreTest {

    private static final Logger log = LogManager.getLogger( ChocoExampleTest.class );

    @Test
    public void testFirstModel() {

        int numberOfLectures = 20;
        Model model = new Model( "Scheduling" );

        IntVar[][] vars = new IntVar[numberOfLectures][4];

        for ( int i = 0; i < numberOfLectures; i++ ) {
            vars[i][0] = model.intVar( "lecture_" + i + "_day", 0, 4 );
            vars[i][1] = model.intVar( "lecture_" + i + "_room", 0, 4 );
            vars[i][2] = model.intVar( "lecture_" + i + "_instructor", 0, 4 );
            vars[i][3] = model.intVar( "lecture_" + i + "_slot", 0, 2 );
        }

        // Lecture - Instructor Constraints
        for ( int i = 0; i < 5; i++ ) {
            model.member( vars[i][2], new int[]{0, 1} );
            model.member( vars[i + 5][2], new int[]{2, 3} );
            model.member( vars[i + 10][2], new int[]{1, 4} );
            model.member( vars[i + 15][2], new int[]{2, 4} );
        }

        // Lecture - Room Constraints
        for ( int i = 0; i < 10; i++ ) {
            model.member( vars[i][1], new int[]{0, 1, 3} );
            model.member( vars[i + 10][1], new int[]{0, 2, 4} );
        }


        for ( int i = 0; i < numberOfLectures; i++ ) {

            // Room - Time Constraints
            model.ifThen( vars[i][1].eq( 0 ).decompose(), vars[i][0].eq( 0 ).decompose());
            model.ifThen( vars[i][1].eq( 1 ).decompose(), model.member( vars[i][0], new int[]{0, 1, 2, 3}));
            model.ifThen( vars[i][1].eq( 2 ).decompose(), model.member( vars[i][0], new int[]{1, 2, 3, 4}));
            model.ifThen( vars[i][1].eq( 3 ).decompose(), vars[i][3].eq( 0 ).decompose());
            model.ifThen( vars[i][1].eq( 4 ).decompose(), vars[i][3].eq( 2 ).decompose());

            // Instructor - Time Constraints
            model.ifThen( vars[i][2].eq( 0 ).decompose(), model.member( vars[i][0], new int[]{1, 2, 3} ) );
            model.ifThen( vars[i][2].eq( 1 ).decompose(), model.member( vars[i][0], new int[]{0, 1, 2} ) );
            model.ifThen( vars[i][2].eq( 2 ).decompose(), model.member( vars[i][0], new int[]{3, 4} ) );
            model.ifThen( vars[i][2].eq( 3 ).decompose(), model.member( vars[i][3], new int[]{1, 2} ));
            model.ifThen( vars[i][2].eq( 4 ).decompose(), model.member( vars[i][3], new int[]{0, 1} ));

        }


        Solution solution = model.getSolver().findSolution();

        final String[] map = new String[]{"Day: ", "Room: ", "Instructor: ", "Slot: "};
        if ( solution != null ) {
            for(int i = 0; i < numberOfLectures; i++) {
                System.out.print( "Lecture "+ (i + 1) + ": ( " );
                for(int j = 0; j < 4; j++) {
                    System.out.print( map[j] + Integer.toString( vars[i][j].getValue() ) + " ");
                }
                System.out.println( " )" );
            }
        }
    }
}
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
    public void testChocoModel() {

        int n = 8;
        Model model = new Model(n + "-queens problem");
        IntVar[] vars = new IntVar[n];



        for(int q = 0; q < n; q++){
            vars[q] = model.intVar("Q_"+q, 1, n);
        }
        for(int i  = 0; i < n-1; i++){
            for(int j = i + 1; j < n; j++){
                model.arithm(vars[i], "!=",vars[j]).post();
                model.arithm(vars[i], "!=", vars[j], "-", j - i).post();
                model.arithm(vars[i], "!=", vars[j], "+", j - i).post();
            }
        }
        Solution solution = model.getSolver().findSolution();
        if(solution != null){
            System.out.println(solution.toString());
        }
    }

    @Test
    public void testFirstModel() {

        int numberOfLectures = 20;
        Model model = new Model("Scheduling");

        IntVar[][] vars = new IntVar[numberOfLectures][4];

        for(int i = 0; i < numberOfLectures; i++) {
            vars[i][0] = model.intVar( "lecture_" + i + "_day", 0, 4 );
            vars[i][1] = model.intVar( "lecture_" + i + "_room", 0, 4 );
            vars[i][2] = model.intVar( "lecture_" + i + "_instructor", 0, 4 );
            vars[i][3] = model.intVar( "lecture_" + i + "_slot", 0, 2 );
        }

        // Lecture Instructor Constraints
        for(int i = 0; i < 5; i++) {
            model.member( vars[i][2], new int[]{0, 1} );
            model.member( vars[i + 5][2], new int[]{2, 3} );
            model.member( vars[i + 10][2], new int[]{1, 4} );
            model.member( vars[i + 15][2], new int[]{2, 4} );
        }

        // Lecture Room Constraints
        for(int i = 0; i < 10; i++) {
            model.member( vars[i][1], new int[]{0, 1, 3} );
            model.member( vars[i + 10][1], new int[]{0, 2, 4} );
        }

        for(int i = 0; i < numberOfLectures; i++) {
            model.addClauses( LogOp.nand( vars[i][1].contains( 0 ) ) );
        }


        Solution solution = model.getSolver().findSolution();

        if(solution != null){
            System.out.println(solution.toString());
        }
    }

    public class InRangeThenPropagator extends Propagator<IntVar> {
        /**
         * Call the main filtering algorithm to apply to the <code>Domain</code> of the <code>Variable</code> objects.
         * It considers the current state of this objects to remove some values from domains and/or instantiate some variables.
         * Calling this method is done from 2 (and only 2) steps:
         * <br/>- at the initial propagation step,
         * <br/>- when involved in a reified constraint.
         * <br/>
         * It should initialized the internal data structure and apply filtering algorithm from scratch.
         *
         * @param evtmask type of propagation event <code>this</code> must consider.
         * @throws ContradictionException when a contradiction occurs, like domain wipe out or other incoherencies.
         */
        @Override
        public void propagate( final int evtmask ) throws ContradictionException {

        }

        /**
         * Check wether <code>this</code> is entailed according to the current state of its internal structure.
         * At least, should check the satisfaction of <code>this</code> (when all is instantiated).
         *
         * @return ESat.TRUE if entailed, ESat.FALSE if not entailed, ESat.UNDEFINED if unknown
         */
        @Override
        public ESat isEntailed() {
            return null;
        }
    }
}

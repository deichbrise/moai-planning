package org.planning.core.examples;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.search.strategy.Search;
import org.chocosolver.solver.variables.IntVar;
import org.junit.Test;
import org.planning.core.test.AbstractCoreTest;

/**
 * @author pascalstammer
 * @version 31.01.17.
 */
public class ChocoExampleTest extends AbstractCoreTest {

    private static final Logger log = LogManager.getLogger( ChocoExampleTest.class );

    @Test
    public void testChocoModel() {

        // 1. Create a Model
        Model model = new Model( "my first problem" );
        // 2. Create variables
        IntVar x = model.intVar( "X", 0, 5 );
        IntVar y = model.intVar( "Y", 0, 5 );
        // 3. Create and post constraints by using constraint factories
        x.add( y ).lt( 5 ).post();
        x.lt( y ).post();
        // 4. Define the search strategy
        model.getSolver().setSearch( Search.inputOrderLBSearch( x, y ) );
        // 5. Launch the resolution process

        while( model.getSolver().solve()) {
            log.info( "X: " + x.getValue() );
            log.info( "Y: " + y.getValue() );
        }
        // 6. Print search statistics
        model.getSolver().printStatistics();
    }
}

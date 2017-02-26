package org.planning.controller.impl;

import org.junit.Test;
import org.planning.controller.PlanningController;
import org.planning.controller.test.AbstractControllerTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pascalstammer
 * @version 26.02.17.
 */
public class PlanningControllerImplTest extends AbstractControllerTest {

    @Autowired
    private PlanningController planningController;

    @Test
    public void testLoadDomainAndExecuteSolver() {
        planningController.loadDomainAndExecuteSolver( "classpath:/domain/example1.xml" );
    }
}

package org.planning.solver.factory.impl;

import org.junit.Test;
import org.planning.persistence.model.Lecture;
import org.planning.solver.test.AbstractSolverTest;
import org.planning.util.GuidGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 */
public class CspModelFactoryTest extends AbstractSolverTest {

    /**
     * We test the csp factory by defining the example model
     *
     * @see org.planning.solver.examples.ChocoExampleTest
     */
    @Test
    public void testCSPExampleModel() {
        final List<Lecture> lectures = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            final Lecture lecture = new Lecture();
            lecture.setGuid(GuidGenerator.generateGuid());
            lecture.setName("Lecture " + i);
        }

    }
}

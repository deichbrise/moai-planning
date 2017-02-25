package org.planning.util.condition;

import org.junit.Test;
import org.planning.util.condition.fixures.FirstEntity;
import org.planning.util.condition.fixures.SecondEntity;
import org.planning.util.condition.fixures.TestEntity;
import org.planning.util.test.AbstractUtilTest;

import static org.junit.Assert.*;

/**
 * @author pascalstammer
 */
public class ConditionsTest extends AbstractUtilTest {

    @Test
    public void testEqualClass() {
        final TestEntity testEntity1 = new FirstEntity();
        final TestEntity testEntity2 = new FirstEntity();
        final TestEntity testEntity3 = new SecondEntity();
        final TestEntity testEntity4 = new SecondEntity();

        assertTrue(Conditions.isOfSameClass(testEntity1.getClass(), testEntity2.getClass()));
        assertTrue(Conditions.isOfSameClass(testEntity3.getClass(), testEntity4.getClass()));

        assertFalse(Conditions.isOfSameClass(testEntity1.getClass(), testEntity3.getClass()));
        assertFalse(Conditions.isOfSameClass(testEntity2.getClass(), testEntity4.getClass()));
    }
}

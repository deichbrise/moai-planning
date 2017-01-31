package org.planning.persistence.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author pascalstammer
 * @version 31.01.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public abstract class AbstractPersistenceTest {


    @Before
    public void setUp() {
        doSetUp();
    }

    @After
    public void tearDown() {
        doTearDown();
    }

    protected void doSetUp() {

    }

    protected void doTearDown() {

    }
}

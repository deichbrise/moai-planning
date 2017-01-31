package org.planning.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author pascalstammer
 * @version 30.01.17.
 */
@SpringBootApplication
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }
}

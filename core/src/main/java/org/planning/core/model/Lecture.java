package org.planning.core.model;

import java.util.List;

/**
 * @author pascalstammer
 * @version 14.02.17.
 */
public class Lecture extends AbstractModel {
    private String name;
    private List<Instructor> supportedInstructors;

    public Lecture() {
    }

    public Lecture( final String name, final List<Instructor> supportedInstructors ) {
        this.name = name;
        this.supportedInstructors = supportedInstructors;
    }

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public List<Instructor> getSupportedInstructors() {
        return supportedInstructors;
    }

    public void setSupportedInstructors( final List<Instructor> supportedInstructors ) {
        this.supportedInstructors = supportedInstructors;
    }


}

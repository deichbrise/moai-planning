package org.planning.persistence.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Instructor extends AbstractModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }
}

package org.planning.domain.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Instructor extends AbstractModel implements DomainModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }
}

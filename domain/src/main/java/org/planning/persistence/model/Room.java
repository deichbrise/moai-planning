package org.planning.persistence.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Room extends AbstractModel implements DomainModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package org.planning.persistence.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public abstract class AbstractModel implements Model {

    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid( final String guid ) {
        this.guid = guid;
    }
}

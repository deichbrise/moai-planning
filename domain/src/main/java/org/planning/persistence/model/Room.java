package org.planning.persistence.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Room extends AbstractModel implements DomainModel {

    private Integer room;

    public Integer getRoom() {
        return room;
    }

    public void setRoom( final Integer room ) {
        this.room = room;
    }
}

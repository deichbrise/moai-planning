package org.planning.domain.model;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Lecture extends AbstractModel implements DomainModel {
    private String name;

    private Instructor instructor;

    private Day day;

    private Room room;

    private TimeSlot timeSlot;

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor( final Instructor instructor ) {
        this.instructor = instructor;
    }

    public Day getDay() {
        return day;
    }

    public void setDay( final Day day ) {
        this.day = day;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom( final Room room ) {
        this.room = room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot( final TimeSlot timeSlot ) {
        this.timeSlot = timeSlot;
    }
}

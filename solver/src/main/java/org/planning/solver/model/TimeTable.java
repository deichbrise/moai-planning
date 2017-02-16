package org.planning.solver.model;

/**
 * @author pascalstammer
 * @version 14.02.17.
 */
public class TimeTable  extends AbstractModel {
    private Integer numberOfRooms;
    private Integer numberOfTimeSlots;
    private Integer numberOfDays;

    private LectureInstructorRelation[][][] lectureInstructorRelations;

    public TimeTable setLecture(final Integer room, final Integer timeSlot, final Integer day, final LectureInstructorRelation lectureInstructorRelation) {
        lectureInstructorRelations[day][timeSlot][room] = lectureInstructorRelation;
        return this;
    }

    public TimeTable( final Integer numberOfRooms, final Integer numberOfTimeSlots, final Integer numberOfDays ) {
        this.numberOfRooms = numberOfRooms;
        this.numberOfTimeSlots = numberOfTimeSlots;
        this.numberOfDays = numberOfDays;
        lectureInstructorRelations = new LectureInstructorRelation[numberOfDays][numberOfTimeSlots][numberOfRooms];
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms( final Integer numberOfRooms ) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberOfTimeSlots() {
        return numberOfTimeSlots;
    }

    public void setNumberOfTimeSlots( final Integer numberOfTimeSlots ) {
        this.numberOfTimeSlots = numberOfTimeSlots;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays( final Integer numberOfDays ) {
        this.numberOfDays = numberOfDays;
    }
}

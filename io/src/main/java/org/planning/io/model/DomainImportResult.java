package org.planning.io.model;

import org.planning.domain.model.*;
import org.planning.domain.model.constraint.Constraint;

import java.util.List;

/**
 * @author pascalstammer
 */
public class DomainImportResult {
    private List<Lecture> lectures;
    private List<Day> days;
    private List<Instructor> instructors;
    private List<TimeSlot> timeSlots;
    private List<Room> rooms;
    private List<Constraint> constraints;

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }
}

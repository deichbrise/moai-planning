package org.planning.core.model;

/**
 * @author pascalstammer
 * @version 14.02.17.
 */
public class LectureInstructorRelation extends AbstractModel {

    private Lecture lecture;
    private Instructor instructor;

    public LectureInstructorRelation() {}

    public LectureInstructorRelation( final Lecture lecture, final Instructor instructor ) {
        this.lecture = lecture;
        this.instructor = instructor;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture( final Lecture lecture ) {
        this.lecture = lecture;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor( final Instructor instructor ) {
        this.instructor = instructor;
    }
}

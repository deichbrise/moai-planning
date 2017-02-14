package org.planning.core.model;

import java.util.List;

/**
 * @author pascalstammer
 * @version 14.02.17.
 */
public class Instructor extends AbstractModel {
    private String name;
    private List<Lecture> lectures;

    public Instructor() {
    }

    public Instructor( final String name, final List<Lecture> lectures ) {
        this.name = name;
        this.lectures = lectures;
    }

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures( final List<Lecture> lectures ) {
        this.lectures = lectures;
    }
}

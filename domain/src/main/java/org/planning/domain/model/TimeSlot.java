package org.planning.domain.model;

import java.sql.Time;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class TimeSlot extends AbstractModel implements DomainModel {

    private Time start;

    private Time end;

    private String name;

    public void setInterval(final Time start, final Time end) {
        this.start = start;
        this.end = end;
    }

    public Time getStart() {
        return start;
    }

    public void setStart( final Time start ) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd( final Time end ) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName( final String name ) {
        this.name = name;
    }
}

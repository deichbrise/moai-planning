package org.planning.persistence.model;

import java.sql.Time;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class TimeSlot {

    private Time start;

    private Time end;

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
}

package org.planning.domain.model;

import java.time.DayOfWeek;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class Day extends AbstractModel implements DomainModel {

    private DayOfWeek day;

    public String getName() {
        return day.toString();
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay( final DayOfWeek day ) {
        this.day = day;
    }
}

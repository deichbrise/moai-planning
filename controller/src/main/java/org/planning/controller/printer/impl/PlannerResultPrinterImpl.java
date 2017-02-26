package org.planning.controller.printer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.planning.controller.printer.PlannerResultPrinter;
import org.planning.domain.model.Lecture;
import org.planning.solver.model.result.PlannerResult;

/**
 * @author pascalstammer
 * @version 26.02.17.
 */
public class PlannerResultPrinterImpl implements PlannerResultPrinter{

    private static Logger log = LogManager.getLogger(PlannerResultPrinter.class);
    @Override
    public void print( final PlannerResult<Lecture> input ) {
        for(Lecture lecture : input.getSolvedAggregateRootEntities()) {
            final StringBuilder builder = new StringBuilder( lecture.getName() );
            builder.append( " Room: " );
            builder.append( lecture.getRoom().getName() );
            builder.append( ", " );
            builder.append( "Time: " );
            builder.append( lecture.getTimeSlot().getName() );
            builder.append( ", " );
            builder.append( "Day: " );
            builder.append( lecture.getDay().getName() );
            builder.append( ", " );
            builder.append( "Instructor: " );
            builder.append( lecture.getInstructor().getName() );
            log.info( builder.toString() );
        }
    }
}

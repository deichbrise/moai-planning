package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.Instructor;
import org.planning.persistence.model.TimeSlot;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class InstructorTimeSlotConstraint extends AbstractBinaryConstraint<Instructor, TimeSlot> {
    public InstructorTimeSlotConstraint( final Instructor model, final Set<TimeSlot> supportedRelations ) {
        super( model, supportedRelations );
    }
}

package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.Room;
import org.planning.persistence.model.TimeSlot;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class RoomTimeSlotConstraint extends AbstractBinaryConstraint<Room, TimeSlot> {
    public RoomTimeSlotConstraint( final Room model, final Set<TimeSlot> supportedRelations ) {
        super( model, supportedRelations );
    }
}

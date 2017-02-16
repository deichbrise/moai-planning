package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.Day;
import org.planning.persistence.model.Room;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class RoomDayConstraint  extends AbstractBinaryConstraint<Room, Day> {
    public RoomDayConstraint( final Room model, final Set<Day> supportedRelations ) {
        super( model, supportedRelations );
    }
}

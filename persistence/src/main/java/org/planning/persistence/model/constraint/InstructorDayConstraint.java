package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.Day;
import org.planning.persistence.model.Instructor;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class InstructorDayConstraint extends AbstractBinaryConstraint<Instructor, Day> {
    public InstructorDayConstraint( final Instructor model, final Set<Day> supportedRelations ) {
        super( model, supportedRelations );
    }
}

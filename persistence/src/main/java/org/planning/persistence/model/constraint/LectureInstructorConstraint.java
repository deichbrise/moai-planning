package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.Instructor;
import org.planning.persistence.model.Lecture;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class LectureInstructorConstraint extends AbstractBinaryConstraint<Lecture, Instructor> {
    public LectureInstructorConstraint( final Lecture model, final Set<Instructor> supportedRelations ) {
        super( model, supportedRelations );
    }
}

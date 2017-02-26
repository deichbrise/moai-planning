package org.planning.solver.factory;

import org.planning.domain.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;

/**
 * Localizes Constraint Descriptor for Constraint Type
 *
 * @author pascalstammer
 */
public interface ConstraintDescriptorFactory {

    /**
     * Get the ConstraintDescriptor for the given constraintType
     * @param constraint the constraint
     * @return the mathcing constraint descriptor
     */
    public ConstraintDescriptor getInstance(final Constraint constraint);
}

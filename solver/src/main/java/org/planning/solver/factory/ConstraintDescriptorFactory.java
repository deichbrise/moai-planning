package org.planning.solver.factory;

import org.planning.persistence.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;

/**
 * Localizes Constraint Descriptor for Constraint Type
 *
 * @author pascalstammer
 */
public interface ConstraintDescriptorFactory {
    public ConstraintDescriptor getInstance(final Constraint constraint);
}

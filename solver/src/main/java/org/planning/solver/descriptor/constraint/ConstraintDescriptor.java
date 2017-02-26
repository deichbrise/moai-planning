package org.planning.solver.descriptor.constraint;

import org.chocosolver.solver.Model;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.solver.model.CspSolvingContext;

/**
 * Implementations of the interface are responsible for constructing constraint description and post this descriptions
 * to the current CSP model
 *
 * @author Pascal Stammer
 */
public interface ConstraintDescriptor {
    public void post(final Model model, final Constraint constraint, final CspSolvingContext context);
    public Class<? extends Constraint> getConstraintClass();
}

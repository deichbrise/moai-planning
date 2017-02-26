package org.planning.solver.descriptor.constraint.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.domain.model.DomainModel;
import org.planning.domain.model.constraint.Constraint;
import org.planning.domain.model.constraint.UniqueConstraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.model.CspSolvingContext;

/**
 * Describe a constraint like no lecture can have same room and instructor at the same time. Example:
 *
 * No two lectures can be given by the same instructor at the same time.
 *
 * @author pascalstammer
 */
public class UniqueConstraintDescriptor extends AbstractConstraintDescriptor implements ConstraintDescriptor {

    @Override
    public void doPost(Model model, Constraint constraint, CspSolvingContext context) {
        final UniqueConstraint uniqueConstraint = (UniqueConstraint)constraint;

        // We have to iterator over each aggregate root entity and each other aggregate root entity to make sure that they will not have same configuration
        for(final DomainModel currentAggregateRootEntity : context.getAggregateRootEntities()) {
            for(final DomainModel otherAggregateRootEntity : context.getAggregateRootEntities()) {
                if(!currentAggregateRootEntity.equals(otherAggregateRootEntity)) {
                    final org.chocosolver.solver.constraints.Constraint[] constraints = new org.chocosolver.solver.constraints.Constraint[uniqueConstraint.getUniqueClassConstraints().size()];
                    int index = 0;

                    // Build and constraints
                    for(final Class<? extends DomainModel> uniqueClass : uniqueConstraint.getUniqueClassConstraints()) {
                        final IntVar var1 = context.getDomain().get(currentAggregateRootEntity.getGuid(), uniqueClass);
                        final IntVar var2 = context.getDomain().get(otherAggregateRootEntity.getGuid(), uniqueClass);
                        constraints[index++] = var1.eq(var2).decompose();
                    }
                    model.not(model.and(constraints)).post();
                }
            }
        }
    }

    @Override
    protected void validate(Model model, Constraint constraint, CspSolvingContext context) {
        if(!isAggregateRootEntity(((UniqueConstraint)constraint).getModel(), context)){
            throwConstraintNotValidException();
        }
    }

    @Override
    public Class<? extends Constraint> getConstraintClass() {
        return UniqueConstraint.class;
    }
}

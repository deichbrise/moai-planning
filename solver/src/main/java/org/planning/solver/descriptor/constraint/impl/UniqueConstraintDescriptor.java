package org.planning.solver.descriptor.constraint.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.persistence.model.constraint.UniqueConstraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.model.CspSolvingContext;

/**
 * @author pascalstammer
 */
public class UniqueConstraintDescriptor extends AbstractConstraintDescriptor implements ConstraintDescriptor {

    @Override
    public void doPost(Model model, Constraint constraint, CspSolvingContext context) {
        final UniqueConstraint uniqueConstraint = (UniqueConstraint)constraint;

        for(final DomainModel currentAggregateRootEntity : context.getAggregateRootEntities()) {
            for(final DomainModel otherAggregateRootEntity : context.getAggregateRootEntities()) {
                if(!currentAggregateRootEntity.equals(otherAggregateRootEntity)) {
                    final org.chocosolver.solver.constraints.Constraint[] constraints = new org.chocosolver.solver.constraints.Constraint[uniqueConstraint.getUniqueClassConstraints().size()];
                    int index = 0;
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

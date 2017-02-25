package org.planning.solver.descriptor.constraint.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.BinaryConstraint;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.model.CspSolvingContext;


/**
 * @author pascalstammer
 */
public class BinaryConstraintDescriptor extends AbstractConstraintDescriptor implements ConstraintDescriptor {

    @Override
    public void doPost(Model model, Constraint constraint, CspSolvingContext context) {
        final BinaryConstraint binaryConstraint = (BinaryConstraint)constraint;

        if(isAggregateRootEntity(binaryConstraint.getModel(), context)) {
            postAggregateRootRelatedEntityConstraint(model, binaryConstraint, context);
        } else {
            postNonAggregateRootRelatedEntityConstraint(model, binaryConstraint, context);
        }
    }

    @Override
    protected void validate(Model model, Constraint constraint, CspSolvingContext context) {
        // Do nothing: we do not have any special requirements of the constraint yet
    }

    @Override
    public Class<? extends Constraint> getConstraintClass() {
        return BinaryConstraint.class;
    }

    protected void postAggregateRootRelatedEntityConstraint(final Model model, final BinaryConstraint constraint, CspSolvingContext context) {
        final String identifier = constraint.getModel().getGuid();
        final Class<? extends DomainModel> relatedClass = getRelatedEntityClass(context);
        final IntVar var = context.getDomain().get(identifier, relatedClass);
        model.member(var, buildDomain(constraint.getSupportedRelations())).post();
    }

    protected void postNonAggregateRootRelatedEntityConstraint(final Model model, final BinaryConstraint constraint, CspSolvingContext context) {
        for(final DomainModel aggregateRootEntity : context.getAggregateRootEntities()) {
            final IntVar var1 = context.getDomain().get(aggregateRootEntity.getGuid(), constraint.getModel().getClass());
            final IntVar var2 = context.getDomain().get(aggregateRootEntity.getGuid(), getRelatedEntityClass(context));
            model.ifThen(var1.eq(getDomainEntityMappingService().get(constraint.getModel())).decompose(), model.member(var2, buildDomain(constraint.getSupportedRelations())));
        }
    }

    protected Class<? extends DomainModel> getRelatedEntityClass(final CspSolvingContext context) {
        return context.getRelatedEntities().iterator().next().getClass();
    }


}

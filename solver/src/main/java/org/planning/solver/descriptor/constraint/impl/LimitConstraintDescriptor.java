package org.planning.solver.descriptor.constraint.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.persistence.model.constraint.LimitConstraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.model.CspSolvingContext;
import org.planning.util.BeanUtil;
import org.planning.util.condition.Conditions;

import java.util.ArrayList;
import java.util.List;

/**
 * We are limiting the number of entity occurrences. Example:
 *
 * No instructor can give more than 5 lectures
 *
 * @author pascalstammer
 */
public class LimitConstraintDescriptor extends AbstractConstraintDescriptor implements ConstraintDescriptor {

    @Override
    public Class<? extends Constraint> getConstraintClass() {
        return LimitConstraint.class;
    }

    @Override
    protected void doPost(Model model, Constraint constraint, CspSolvingContext context) {
        final LimitConstraint limitConstraint = (LimitConstraint)constraint;

        // Collect all model vars of the entity type
        final List<IntVar> allModelConstraints = new ArrayList<>();
        for(final DomainModel aggregateRootEntitiy : context.getAggregateRootEntities()) {
            allModelConstraints.add(context.getDomain().get(aggregateRootEntitiy.getGuid(), limitConstraint.getModelClass()));
        }
        final IntVar[] allModellVars = (IntVar[]) allModelConstraints.toArray();

        // Specify the count domain
        IntVar limit = model.intVar(limitConstraint.getModelClass().getSimpleName() + "_limit", 0, limitConstraint.getLimit());
        final int[] domain = buildDomain(getDomainModelOfClass(context, limitConstraint.getModelClass()));

        // Configure the constraint
        for( final int domainEntityId : domain ) {
            model.count( domainEntityId, allModellVars, limit ).post();
        }
    }

    @Override
    protected void validate(Model model, Constraint constraint, CspSolvingContext context) {
        if(isAggregateRootEntity(((LimitConstraint)constraint).getModelClass(), context)) {
            throwConstraintNotValidException();
        }
    }

    protected List<DomainModel> getDomainModelOfClass(final CspSolvingContext context, final Class<? extends DomainModel> clazz) {
        final List<DomainModel> result = new ArrayList<>();
        for(final DomainModel domainModel : context.getRelatedEntities()) {
            if(Conditions.isOfSameClass(domainModel.getClass(), clazz)) {
                result.add(domainModel);
            }
        }
        return result;
    }
}

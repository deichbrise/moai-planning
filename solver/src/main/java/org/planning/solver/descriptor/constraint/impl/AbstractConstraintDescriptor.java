package org.planning.solver.descriptor.constraint.impl;

import org.chocosolver.solver.Model;
import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.service.DomainEntityMappingService;
import org.planning.util.condition.Conditions;
import org.planning.util.exception.PlanningRuntimeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author pascalstammer
 */
public abstract class AbstractConstraintDescriptor implements ConstraintDescriptor {

    private DomainEntityMappingService domainEntityMappingService;

    @Override
    public void post(final Model model, final Constraint constraint, final CspSolvingContext context) {
        validate(model, constraint, context);
        doPost(model, constraint, context);
    }

    protected  abstract void doPost(final Model model, final Constraint constraint, final CspSolvingContext context);
    protected  abstract void validate(final Model model, final Constraint constraint, final CspSolvingContext context);

    protected boolean isAggregateRootEntity(final DomainModel domainModel, CspSolvingContext context) {
        return isAggregateRootEntity(domainModel.getClass(), context);
    }

    protected boolean isAggregateRootEntity(final Class<? extends DomainModel> domainModelClass, CspSolvingContext context) {
        final Class<? extends DomainModel> aggregateRootClass = context.getAggregateRootEntities().iterator().next().getClass();
        return Conditions.isOfSameClass(aggregateRootClass, domainModelClass);
    }

    protected int[] buildDomain(final Collection<DomainModel> domainModels) {
        return buildDomain(new ArrayList<DomainModel>(domainModels));
    }
    protected int[] buildDomain(final List<DomainModel> domainModels) {
        final int[] domainTable = new int[domainModels.size()];

        for(int i = 0; i < domainModels.size(); i++) {
            domainTable[i] = getDomainEntityMappingService().get(domainModels.get(i));
        }

        return domainTable;
    }

    protected void throwConstraintNotValidException() {
        throw new PlanningRuntimeException("The Constraint is not valid.");
    }

    public DomainEntityMappingService getDomainEntityMappingService() {
        return domainEntityMappingService;
    }

    public void setDomainEntityMappingService(DomainEntityMappingService domainEntityMappingService) {
        this.domainEntityMappingService = domainEntityMappingService;
    }
}

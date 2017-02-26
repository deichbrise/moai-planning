package org.planning.solver.factory.impl;

import org.chocosolver.solver.Model;
import org.planning.domain.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.descriptor.domain.DomainDescriptor;
import org.planning.solver.factory.ConstraintDescriptorFactory;
import org.planning.solver.factory.CspModelFactory;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.Domain;
import org.planning.util.annotation.Since;

/**
 * Initialize Domain and then post constraints to model via constraints descriptors
 *
 * @author Pascal Stammer
 */
@Since("1.0.0")
public class CspModelFactoryImpl implements CspModelFactory {

    private DomainDescriptor domainDescriptor;
    private ConstraintDescriptorFactory constraintDescriptorFactory;

    @Override
    public Model createModel(final CspSolvingContext context) {

        // Create Model
        final Model model = initializeModel(context);

        // Initialize the Domain
        initializeDomain(model, context);

        // Initialize Constraints
        initializeConstraints(model, context);

        return model;
    }

    protected Model initializeModel(final CspSolvingContext context) {
        return new Model();
    }

    protected void initializeDomain(final Model model, final CspSolvingContext context) {
        final Domain domain = getDomainDescriptor().describe(model, context.getAggregateRootEntities(), context.getRelatedEntities());
        context.setDomain(domain);
    }

    protected void initializeConstraints(final Model model, final CspSolvingContext context) {
        for (final Constraint constraint : context.getConstraints()) {
            postConstraint(model, constraint, context);
        }
    }

    protected void postConstraint(final Model model, final Constraint constraint, final CspSolvingContext context) {
        final ConstraintDescriptor constraintDescriptor = getConstraintDescriptorFactory().getInstance(constraint);
        constraintDescriptor.post(model, constraint, context);
    }

    public DomainDescriptor getDomainDescriptor() {
        return domainDescriptor;
    }

    public void setDomainDescriptor(DomainDescriptor domainDescriptor) {
        this.domainDescriptor = domainDescriptor;
    }

    public ConstraintDescriptorFactory getConstraintDescriptorFactory() {
        return constraintDescriptorFactory;
    }

    public void setConstraintDescriptorFactory(ConstraintDescriptorFactory constraintDescriptorFactory) {
        this.constraintDescriptorFactory = constraintDescriptorFactory;
    }
}

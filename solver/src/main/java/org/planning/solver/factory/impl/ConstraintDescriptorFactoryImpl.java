package org.planning.solver.factory.impl;

import org.planning.domain.model.constraint.Constraint;
import org.planning.solver.descriptor.constraint.ConstraintDescriptor;
import org.planning.solver.factory.ConstraintDescriptorFactory;
import org.planning.util.annotation.Since;
import org.planning.util.exception.PlanningRuntimeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pascalstammer
 */
@Since("1.0.0")
public class ConstraintDescriptorFactoryImpl implements ConstraintDescriptorFactory {

    private List<ConstraintDescriptor> constraintDescriptors;
    private Map<String, ConstraintDescriptor> constraintDescriptorIndex;

    public void init() {
        buildConstraintDescriptorIndex();
    }

    @Since("1.0.0")
    @Override
    public ConstraintDescriptor getInstance(Constraint constraint) {
        if(!constraintDescriptorIndex.containsKey(constraint.getClass().getName())) {
            throwDescriptorNotFoundException(constraint);
        }
        return constraintDescriptorIndex.get(constraint.getClass().getName());
    }

    protected void buildConstraintDescriptorIndex() {
        constraintDescriptorIndex = new HashMap<>();
        for(ConstraintDescriptor constraintDescriptor : getConstraintDescriptors()) {
            constraintDescriptorIndex.put(constraintDescriptor.getConstraintClass().getName(), constraintDescriptor);
        }
    }

    protected void throwDescriptorNotFoundException(final Constraint constraint) {
        throw new PlanningRuntimeException("ConstraintDescriptor not found for " + constraint.getClass().getName());
    }

    public List<ConstraintDescriptor> getConstraintDescriptors() {
        return constraintDescriptors;
    }

    public void setConstraintDescriptors(List<ConstraintDescriptor> constraintDescriptors) {
        this.constraintDescriptors = constraintDescriptors;
    }
}

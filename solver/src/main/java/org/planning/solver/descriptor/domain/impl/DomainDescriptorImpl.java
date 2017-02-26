package org.planning.solver.descriptor.domain.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.DomainModel;
import org.planning.solver.descriptor.domain.DomainDescriptor;
import org.planning.solver.model.Domain;
import org.planning.solver.service.DomainEntityMappingService;
import org.planning.util.BeanUtil;
import org.planning.util.annotation.Since;
import org.planning.util.exception.PlanningRuntimeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provide the entire domain description
 *
 * @author Pascal Stammer
 */
@Since("1.0.0")
public class DomainDescriptorImpl implements DomainDescriptor {

    private DomainEntityMappingService domainEntityMappingService;

    @Override
    @Since("1.0.0")
    public Domain describe(Model model, List<DomainModel> aggregateRootEntities, List<DomainModel> relatedEntities) {
        return initializeVariables(model, aggregateRootEntities, relatedEntities);
    }

    protected Domain initializeVariables(Model model, List<DomainModel> aggregateRootEntities, List<DomainModel> relatedEntities) {
        final Map<Class<? extends DomainModel>, List<DomainModel>> relatedEntityIndex = initRelatedEntityIndex(relatedEntities);
        final Domain domain = new Domain();

        // Each property of the aggregate root entities get its own IntVar
        for(int i = 0; i < aggregateRootEntities.size(); i++) {
            final DomainModel aggregateRootEntity = aggregateRootEntities.get(i);
            initializeVariablesForAggregateRootEntity(model, aggregateRootEntity, relatedEntityIndex, domain);
        }
        return domain;
    }

    protected void initializeVariablesForAggregateRootEntity(Model model, DomainModel aggregateRootEntity, Map<Class<? extends DomainModel>, List<DomainModel>> relatedEntityIndex, final Domain domain) {
        for(Map.Entry<Class<? extends DomainModel>, List<DomainModel>> entry : relatedEntityIndex.entrySet()) {
            final IntVar intVar = initializeVariableForAggregateRootAndRelateEntity(model, aggregateRootEntity, entry.getKey(), entry.getValue());
            domain.put(aggregateRootEntity.getGuid(), entry.getKey(), intVar);
        }
    }

    protected IntVar initializeVariableForAggregateRootAndRelateEntity(final Model model, DomainModel aggregateRootEntity, Class<?> relatedEntityClass, List<DomainModel> relatedEntities) {
        validate(aggregateRootEntity, relatedEntityClass);
        final int[] domain = new int[relatedEntities.size()];
        for(int i = 0; i < domain.length; i++) {
            domain[i] = getDomainEntityMappingService().get(relatedEntities.get(i));
        }

        return model.intVar(getVarName(aggregateRootEntity, relatedEntityClass), domain);
    }

    protected String getVarName(DomainModel aggregateEntity, Class<?> relatedEntityClass) {
        final StringBuilder builder = new StringBuilder(aggregateEntity.getGuid());
        builder.append("_");
        builder.append(relatedEntityClass.getSimpleName().toLowerCase());

        return builder.toString();
    }

    protected void validate(DomainModel aggregateRootEntity, Class<?> relatedEntityClass) {
        if(!BeanUtil.hasPropertyOfClass(aggregateRootEntity.getClass(), relatedEntityClass)) {
            throwAggreagateRootNoSuchProperty(aggregateRootEntity, relatedEntityClass);
        }
    }

    protected void throwAggreagateRootNoSuchProperty(DomainModel aggregateRoot, Class<?> noSuchClass) {
        final StringBuilder builder = new StringBuilder("Aggregate Root of Class ");
        builder.append(aggregateRoot.getClass().getName());
        builder.append(" has no attribute of type ");
        builder.append(noSuchClass.getName());
        builder.append(".");

        throw new PlanningRuntimeException(builder.toString());
    }

    protected Map<Class<? extends DomainModel>, List<DomainModel>> initRelatedEntityIndex(List<DomainModel> relatedEntities) {
        final Map<Class<? extends DomainModel>, List<DomainModel>> relatedEntityIndex = new HashMap<>();
        for(DomainModel entity: relatedEntities) {
            if(relatedEntityIndex.containsKey(entity.getClass())) {
                relatedEntityIndex.get(entity.getClass()).add(entity);
            } else {
                final List<DomainModel> bufferList = new ArrayList<>();
                bufferList.add(entity);
                relatedEntityIndex.put(entity.getClass(), bufferList);
            }
        }
        return relatedEntityIndex;
    }

    public DomainEntityMappingService getDomainEntityMappingService() {
        return domainEntityMappingService;
    }

    public void setDomainEntityMappingService(DomainEntityMappingService domainEntityMappingService) {
        this.domainEntityMappingService = domainEntityMappingService;
    }
}

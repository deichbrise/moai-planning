package org.planning.solver.model.impl;

import org.planning.domain.model.DomainModel;
import org.planning.solver.model.EntityIdentifierIndex;
import org.planning.util.exception.PlanningRuntimeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pascalstammer on 23.02.17.
 */
public class MapEntitiyIdentifierIndex implements EntityIdentifierIndex {

    private Map<Integer, DomainModel> numericIdentifierToDomainModelIndex = new HashMap<>();
    private Map<String, Integer> stringIdentifierToNumericIdentifierIndex = new HashMap<>();
    private final Object blockable = new Object();

    @Override
    public void add(final DomainModel domainModel) {
        synchronized (blockable) {
            final String identifier = domainModel.getGuid();
            final Integer numericIdentifier = stringIdentifierToNumericIdentifierIndex.size();
            if(stringIdentifierToNumericIdentifierIndex.containsKey(identifier)) {
                throw new PlanningRuntimeException("Id already exists in index.");
            } else {
                stringIdentifierToNumericIdentifierIndex.put(identifier, numericIdentifier);
                numericIdentifierToDomainModelIndex.put(numericIdentifier, domainModel);
            }
        }
    }

    @Override
    public Integer get(final DomainModel domainModel) {
        return stringIdentifierToNumericIdentifierIndex.get(domainModel.getGuid());
    }

    @Override
    public DomainModel get(final Integer identifier) {
        return numericIdentifierToDomainModelIndex.get(identifier);
    }

    @Override
    public boolean contains(final DomainModel domainModel) {
        return stringIdentifierToNumericIdentifierIndex.containsKey(domainModel.getGuid());
    }
}

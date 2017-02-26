package org.planning.solver.service.impl;

import org.planning.domain.model.DomainModel;
import org.planning.solver.service.DomainEntityMappingService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pascalstammer
 */
public class DomainEntityMappingServiceImpl implements DomainEntityMappingService {

    final private Map<String, Integer> stringIdentifierToNumericIdentifierIndex = new HashMap<>();
    final private Map<Integer, DomainModel> numericIdentifierToDomainModelIndex = new HashMap<>();
    final private Object blockable = new Object();

    @Override
    public Integer get(DomainModel domainModel) {
        synchronized (blockable) {
            final String guid = domainModel.getGuid();
            if(stringIdentifierToNumericIdentifierIndex.containsKey(guid)) {
                return stringIdentifierToNumericIdentifierIndex.get(guid);
            } else {
                final Integer nextId = stringIdentifierToNumericIdentifierIndex.size();
                stringIdentifierToNumericIdentifierIndex.put(guid, nextId);
                numericIdentifierToDomainModelIndex.put(nextId, domainModel);
                return nextId;
            }
        }
    }

    @Override
    public DomainModel get(Integer identifier) {
        synchronized (blockable) {
            if(!numericIdentifierToDomainModelIndex.containsKey(identifier)) {
                return null;
            } else {
                return numericIdentifierToDomainModelIndex.get(identifier);
            }
        }
    }
}

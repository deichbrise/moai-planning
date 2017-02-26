package org.planning.solver.service;

import org.planning.domain.model.DomainModel;

/**
 * @author pascalstammer
 */
public interface DomainEntityMappingService {
    public Integer get(final DomainModel domainModel);
    public DomainModel get(final Integer identifier);
}

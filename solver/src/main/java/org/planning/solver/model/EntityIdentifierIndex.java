package org.planning.solver.model;

import org.planning.persistence.model.DomainModel;

/**
 * Created by pascalstammer on 23.02.17.
 */
public interface EntityIdentifierIndex {
    public void add(final DomainModel domainModel);
    public Integer get(final DomainModel domainModel);
    public DomainModel get(final Integer identifier);
    public boolean contains(final DomainModel domainModel);
}

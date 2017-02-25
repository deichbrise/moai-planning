package org.planning.solver.model;

import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.Constraint;

import java.util.List;

/**
 * Created by pascalstammer on 23.02.17.
 */
public class CspSolvingContext {
    private List<DomainModel> aggregateRootEntities;
    private List<DomainModel> relatedEntities;
    private Domain domain;
    private List<Constraint> constraints;

    public List<DomainModel> getAggregateRootEntities() {
        return aggregateRootEntities;
    }

    public void setAggregateRootEntities(List<DomainModel> aggregateRootEntities) {
        this.aggregateRootEntities = aggregateRootEntities;
    }

    public List<DomainModel> getRelatedEntities() {
        return relatedEntities;
    }

    public void setRelatedEntities(List<DomainModel> relatedEntities) {
        this.relatedEntities = relatedEntities;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }
}

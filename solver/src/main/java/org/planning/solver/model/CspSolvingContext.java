package org.planning.solver.model;

import org.planning.persistence.model.DomainModel;
import org.planning.persistence.model.constraint.Constraint;

import java.util.List;

/**
 * Created by pascalstammer on 23.02.17.
 */
public class CspSolvingContext {
    private List<? extends DomainModel> aggregateRootEntities;
    private List<? extends DomainModel> relatedEntities;
    private Domain domain;
    private List<Constraint> constraints;

    public List<? extends DomainModel> getAggregateRootEntities() {
        return aggregateRootEntities;
    }

    public void setAggregateRootEntities(List<? extends DomainModel> aggregateRootEntities) {
        this.aggregateRootEntities = aggregateRootEntities;
    }

    public List<? extends DomainModel> getRelatedEntities() {
        return relatedEntities;
    }

    public void setRelatedEntities(List<? extends DomainModel> relatedEntities) {
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

package org.planning.persistence.model.constraint;

import org.planning.persistence.model.DomainModel;

/**
 * @author pascalstammer
 */
public class LimitConstraint implements Constraint {
    private Class<? extends DomainModel> modelClass;
    private Integer limit;

    public LimitConstraint(Class<? extends DomainModel> modelClass, Integer limit) {
        this.modelClass = modelClass;
        this.limit = limit;
    }

    public Class<? extends DomainModel> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<? extends DomainModel> modelClass) {
        this.modelClass = modelClass;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

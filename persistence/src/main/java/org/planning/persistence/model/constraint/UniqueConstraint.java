package org.planning.persistence.model.constraint;

import org.planning.persistence.model.DomainModel;

import java.util.Set;

/**
 * @author pascalstammer
 */
public class UniqueConstraint implements Constraint {

    private Class<? extends DomainModel> model;
    private Set<Class<? extends DomainModel>> uniqueClassConstraints;

    public UniqueConstraint(Class<? extends DomainModel> model, Set<Class<? extends DomainModel>> uniqueClassConstraints) {
        this.model = model;
        this.uniqueClassConstraints = uniqueClassConstraints;
    }

    public Class<? extends DomainModel> getModel() {
        return model;
    }

    public void setModel(Class<? extends DomainModel> model) {
        this.model = model;
    }

    public Set<Class<? extends DomainModel>> getUniqueClassConstraints() {
        return uniqueClassConstraints;
    }

    public void setUniqueClassConstraints(Set<Class<? extends DomainModel>> uniqueClassConstraints) {
        this.uniqueClassConstraints = uniqueClassConstraints;
    }
}

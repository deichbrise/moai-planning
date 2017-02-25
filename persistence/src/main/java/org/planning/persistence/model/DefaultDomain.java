package org.planning.persistence.model;

import java.util.List;

/**
 * Created by pascalstammer on 23.02.17.
 */
public class DefaultDomain<M, R> {
    private Class<M> modelClass;
    private List<R> defaultDomainObjects;
    private Class<R> defaultDomainObjectClass;

    public Class<M> getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class<M> modelClass) {
        this.modelClass = modelClass;
    }

    public List<R> getDefaultDomainObjects() {
        return defaultDomainObjects;
    }

    public void setDefaultDomainObjects(List<R> defaultDomainObjects) {
        this.defaultDomainObjects = defaultDomainObjects;
    }

    public Class<R> getDefaultDomainObjectClass() {
        return defaultDomainObjectClass;
    }

    public void setDefaultDomainObjectClass(Class<R> defaultDomainObjectClass) {
        this.defaultDomainObjectClass = defaultDomainObjectClass;
    }
}

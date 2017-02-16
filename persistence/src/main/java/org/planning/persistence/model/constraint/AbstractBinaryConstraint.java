package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public abstract class AbstractBinaryConstraint<T, R> extends AbstractModel implements BinaryConstraint<T, R> {

    private T model;

    private Set<R> supportedRelations;

    public AbstractBinaryConstraint( final T model, final Set<R> supportedRelations ) {
        this.model = model;
        this.supportedRelations = supportedRelations;
    }

    @Override
    public T getModel() {
        return model;
    }

    public void setModel( final T model ) {
        this.model = model;
    }

    @Override
    public Set<R> getSupportedRelations() {
        return supportedRelations;
    }

    public void setSupportedRelations( final Set<R> supportedRelations ) {
        this.supportedRelations = supportedRelations;
    }
}

package org.planning.persistence.model.constraint;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public interface BinaryConstraint<T, R> extends Constraint {

    T getModel();
    void setModel(T model);
    Set<R> getSupportedRelations();
    void setSupportedRelations(Set<R> supportedRelations);
}

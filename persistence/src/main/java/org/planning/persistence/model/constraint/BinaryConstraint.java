package org.planning.persistence.model.constraint;

import org.planning.persistence.model.AbstractModel;
import org.planning.persistence.model.DomainModel;

import java.util.Set;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class BinaryConstraint extends AbstractModel implements Constraint {

    private DomainModel model;

    private Set<DomainModel> supportedRelations;

    public BinaryConstraint( final DomainModel model, final Set<DomainModel> supportedRelations ) {
        this.model = model;
        this.supportedRelations = supportedRelations;
    }

    public DomainModel getModel() {
        return model;
    }

    public void setModel( final DomainModel model ) {
        this.model = model;
    }

    public Set<DomainModel> getSupportedRelations() {
        return supportedRelations;
    }

    public void setSupportedRelations( final Set<DomainModel> supportedRelations ) {
        this.supportedRelations = supportedRelations;
    }
}

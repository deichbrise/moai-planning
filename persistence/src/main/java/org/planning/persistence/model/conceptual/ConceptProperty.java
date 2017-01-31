package org.planning.persistence.model.conceptual;

import org.neo4j.ogm.annotation.GraphId;
import org.planning.persistence.model.AbstractModel;

/**
 * @author pascalstammer
 * @version 30.01.17.
 */
public class ConceptProperty extends AbstractModel {
    @GraphId private Long id;

    private String propertyName;

    public Long getId() {
        return id;
    }

    public void setId( final Long id ) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName( final String propertyName ) {
        this.propertyName = propertyName;
    }
}

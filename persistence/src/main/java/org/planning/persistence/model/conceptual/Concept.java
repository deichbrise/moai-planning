package org.planning.persistence.model.conceptual;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.planning.persistence.model.AbstractModel;

/**
 * @author pascalstammer
 * @version 30.01.17.
 */
@NodeEntity
public class Concept extends AbstractModel {
    @GraphId private Long id;

    private String name;
}

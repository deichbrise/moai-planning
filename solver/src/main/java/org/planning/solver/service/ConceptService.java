package org.planning.solver.service;

import org.planning.persistence.model.conceptual.Concept;
import org.planning.persistence.model.conceptual.ConceptProperty;

import java.util.Collection;

/**
 * @author pascalstammer
 * @version 30.01.17.
 */
public interface ConceptService {
    Collection<ConceptProperty> getPropertiesOfConcept( final Concept concept );
    void saveConceptWithProperties(final Concept concept, final Collection<ConceptProperty> conceptProperties);
}

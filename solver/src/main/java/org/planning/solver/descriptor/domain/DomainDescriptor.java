package org.planning.solver.descriptor.domain;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.DomainModel;
import org.planning.solver.model.Domain;
import org.planning.util.annotation.Since;

import java.util.List;

/**
 * Implementations of this interface constructing the underlying domain on which successor steps are operating
 * @author Pascal Stammer
 */
@Since("1.0.0")
public interface DomainDescriptor {

    @Since("1.0.0")
    public Domain describe(final Model model, List<DomainModel> aggregateRootEntities, List<DomainModel> relatedEntities);
}

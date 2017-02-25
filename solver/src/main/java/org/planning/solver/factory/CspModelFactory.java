package org.planning.solver.factory;

import org.chocosolver.solver.Model;
import org.planning.solver.model.CspSolvingContext;

/**
 * Initialize Model including Domain and Constraints
 *
 * @author Pascal Stammer
 */
public interface CspModelFactory {

    public Model createModel(final CspSolvingContext context);
}

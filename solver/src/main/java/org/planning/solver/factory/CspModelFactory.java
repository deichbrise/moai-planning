package org.planning.solver.factory;

import org.chocosolver.solver.Model;
import org.planning.solver.model.CspSolvingContext;

/**
 * Initialize Model including Domain and Constraints
 *
 * @author Pascal Stammer
 */
public interface CspModelFactory {

    /**
     * Create Model
     * @param context the csp solving context
     * @return the initialized model
     */
    public Model createModel(final CspSolvingContext context);
}

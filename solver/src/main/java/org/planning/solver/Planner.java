package org.planning.solver;

import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;

/**
 * @author pascalstammer
 */
public interface Planner {
    PlannerResult solve(final CspSolvingContext context);
}

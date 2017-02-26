package org.planning.solver.resolver;

import org.chocosolver.solver.Solution;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;

/**
 * @author pascalstammer
 */
public interface DomainResolver<T> {
    public void resolve(final PlannerResult<T> plannerResult, final CspSolvingContext context, final Solution solution);
}

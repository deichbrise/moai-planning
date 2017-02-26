package org.planning.solver.impl;

import org.chocosolver.solver.Model;
import org.planning.solver.Planner;
import org.planning.solver.factory.CspModelFactory;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;

/**
 * @author pascalstammer
 */
public class PlannerImpl implements Planner {

    private CspModelFactory cspModelFactory;
    @Override
    public PlannerResult solve(CspSolvingContext context) {
        final PlannerResult plannerResult = new PlannerResult();

        final Model model = getCspModelFactory().createModel(context);

        model.getSolver().solve();

        return plannerResult;
    }

    public CspModelFactory getCspModelFactory() {
        return cspModelFactory;
    }

    public void setCspModelFactory(CspModelFactory cspModelFactory) {
        this.cspModelFactory = cspModelFactory;
    }
}

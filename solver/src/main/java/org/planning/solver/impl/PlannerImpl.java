package org.planning.solver.impl;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.planning.domain.model.Lecture;
import org.planning.solver.Planner;
import org.planning.solver.factory.CspModelFactory;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;
import org.planning.solver.resolver.DomainResolver;

/**
 * @author pascalstammer
 */
public class PlannerImpl implements Planner {

    private CspModelFactory cspModelFactory;
    private DomainResolver<Lecture> domainResolver;

    @Override
    public PlannerResult solve(CspSolvingContext context) {
        final PlannerResult plannerResult = new PlannerResult();

        final Model model = getCspModelFactory().createModel(context);
        final Solution solution = model.getSolver().findSolution();
        getDomainResolver().resolve( plannerResult, context, solution );
        return plannerResult;
    }

    public CspModelFactory getCspModelFactory() {
        return cspModelFactory;
    }

    public void setCspModelFactory(CspModelFactory cspModelFactory) {
        this.cspModelFactory = cspModelFactory;
    }

    public DomainResolver<Lecture> getDomainResolver() {
        return domainResolver;
    }

    public void setDomainResolver( final DomainResolver<Lecture> domainResolver ) {
        this.domainResolver = domainResolver;
    }
}

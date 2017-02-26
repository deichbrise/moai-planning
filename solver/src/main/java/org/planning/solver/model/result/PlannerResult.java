package org.planning.solver.model.result;

import org.chocosolver.solver.Solution;
import org.planning.persistence.model.DomainModel;

import java.util.List;

/**
 * @author pascalstammer
 */
public class PlannerResult<T> {

    private List<T> solvedAggregateRootEntities;
    private boolean success;
    private Solution solution;

    public List<T> getSolvedAggregateRootEntities() {
        return solvedAggregateRootEntities;
    }

    public void setSolvedAggregateRootEntities(List<T> solvedAggregateRootEntities) {
        this.solvedAggregateRootEntities = solvedAggregateRootEntities;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }
}

package org.planning.controller.impl;

import org.planning.controller.PlanningController;
import org.planning.controller.printer.PlannerResultPrinter;
import org.planning.domain.model.Lecture;
import org.planning.io.model.DomainImportResult;
import org.planning.io.service.ImportService;
import org.planning.domain.model.DomainModel;
import org.planning.solver.Planner;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 */
public class PlanningControllerImpl implements PlanningController {


    private ImportService importService;

    private Planner planner;

    private PlannerResultPrinter plannerResultPrinter;

    @Override
    public void loadDomainAndExecuteSolver(String pathToFile) {
        final DomainImportResult domain = getImportService().importDomainFromDescriptionFile(pathToFile);

        // Create Solving Context
        final CspSolvingContext solvingContext = new CspSolvingContext();

        // Lectures are aggregate root entities
        solvingContext.setAggregateRootEntities(domain.getLectures());

        // Build related entities and add to solving context
        final List<DomainModel> relatedDomainEntities = new ArrayList<>();
        relatedDomainEntities.addAll(domain.getDays());
        relatedDomainEntities.addAll(domain.getRooms());
        relatedDomainEntities.addAll(domain.getInstructors());
        relatedDomainEntities.addAll(domain.getTimeSlots());

        solvingContext.setRelatedEntities(relatedDomainEntities);

        // Add constraints to solving context
        solvingContext.setConstraints(domain.getConstraints());

        // Execute Planner
        final PlannerResult<Lecture> result = getPlanner().solve(solvingContext);

        getPlannerResultPrinter().print( result );
    }

    public ImportService getImportService() {
        return importService;
    }

    public void setImportService(ImportService importService) {
        this.importService = importService;
    }

    public Planner getPlanner() {
        return planner;
    }

    public void setPlanner(Planner planner) {
        this.planner = planner;
    }

    public PlannerResultPrinter getPlannerResultPrinter() {
        return plannerResultPrinter;
    }

    public void setPlannerResultPrinter( final PlannerResultPrinter plannerResultPrinter ) {
        this.plannerResultPrinter = plannerResultPrinter;
    }
}

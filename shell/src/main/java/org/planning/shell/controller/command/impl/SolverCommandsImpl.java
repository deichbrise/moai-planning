package org.planning.shell.controller.command.impl;

import org.planning.controller.PlanningController;
import org.planning.io.service.ImportService;
import org.planning.shell.controller.command.SolverCommands;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;

/**
 * @author pascalstammer
 */
public class SolverCommandsImpl extends AbstractCommands implements SolverCommands {

    private PlanningController planningController;

    @CliCommand(value = "solve", help = "validate the domain description")
    public void importAndRun(@CliOption(key = {"", "file"}) String file) {
        getPlanningController().loadDomainAndExecuteSolver(file);
    }

    public PlanningController getPlanningController() {
        return planningController;
    }

    public void setPlanningController(PlanningController planningController) {
        this.planningController = planningController;
    }
}

package org.planning.controller.printer;

import org.planning.domain.model.Lecture;
import org.planning.solver.model.result.PlannerResult;

/**
 * @author pascalstammer
 * @version 26.02.17.
 */
public interface PlannerResultPrinter extends Printer<PlannerResult<Lecture>> {
}

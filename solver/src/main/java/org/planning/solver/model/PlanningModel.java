package org.planning.solver.model;

import org.planning.persistence.model.Model;
import org.planning.persistence.model.constraint.Constraint;

import java.util.Collection;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public interface PlanningModel {

    Collection<Model> getModels();
    Collection<Constraint> getConstraints();
}

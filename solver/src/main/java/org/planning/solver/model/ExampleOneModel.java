package org.planning.solver.model;

import org.planning.persistence.model.Model;
import org.planning.persistence.model.constraint.Constraint;

import java.util.Collection;

/**
 * @author pascalstammer
 * @version 16.02.17.
 */
public class ExampleOneModel extends AbstractPlanningModel {

    private Collection<Model> models;
    private Collection<Constraint> constraints;

    public ExampleOneModel() {



    }

    @Override
    public Collection<Model> getModels() {
        return models;
    }

    @Override
    public Collection<Constraint> getConstraints() {
        return constraints;
    }
}

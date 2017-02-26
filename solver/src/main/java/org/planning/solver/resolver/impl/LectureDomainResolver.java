package org.planning.solver.resolver.impl;

import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;
import org.planning.persistence.model.*;
import org.planning.solver.model.CspSolvingContext;
import org.planning.solver.model.result.PlannerResult;
import org.planning.solver.resolver.DomainResolver;
import org.planning.solver.service.DomainEntityMappingService;

import java.util.ArrayList;

/**
 * Map the Choco Solver result to our own data model for further processing
 *
 * @author pascalstammer
 */
public class LectureDomainResolver implements DomainResolver<Lecture> {

    private DomainEntityMappingService domainEntityMappingService;

    @Override
    public void resolve(final PlannerResult<Lecture> plannerResult, final CspSolvingContext context, final Solution solution) {
        plannerResult.setSolution(solution);
        if(solution == null) {
            plannerResult.setSuccess(false);
        } else {
            plannerResult.setSuccess(true);
            for(final DomainModel domainModel : context.getAggregateRootEntities()) {
                resolveForEntity(plannerResult, context, domainModel);
            }
        }
    }

    protected void resolveForEntity(final PlannerResult<Lecture> plannerResult, final CspSolvingContext context, final DomainModel domainModel) {
        final Lecture lecture = (Lecture)domainModel;

        final Day day = (Day)getDomainModelForLectureAndClass(lecture, Day.class, context);
        final Room room = (Room) getDomainModelForLectureAndClass(lecture, Room.class, context);;
        final TimeSlot timeSlot = (TimeSlot) getDomainModelForLectureAndClass(lecture, TimeSlot.class, context);;
        final Instructor instructor = (Instructor) getDomainModelForLectureAndClass(lecture, Instructor.class, context);

        lecture.setDay(day);
        lecture.setInstructor(instructor);
        lecture.setRoom(room);
        lecture.setTimeSlot(timeSlot);

        if(plannerResult.getSolvedAggregateRootEntities() == null) {
            plannerResult.setSolvedAggregateRootEntities(new ArrayList<>());
        }

        plannerResult.getSolvedAggregateRootEntities().add(lecture);
    }

    protected DomainModel getDomainModelForLectureAndClass(final Lecture lecture, final Class<? extends DomainModel> domainModelClass, final CspSolvingContext context ) {
        final IntVar var = context.getDomain().get(lecture.getGuid(), domainModelClass);
        return getDomainEntityMappingService().get(var.getValue());
    }

    public DomainEntityMappingService getDomainEntityMappingService() {
        return domainEntityMappingService;
    }

    public void setDomainEntityMappingService(DomainEntityMappingService domainEntityMappingService) {
        this.domainEntityMappingService = domainEntityMappingService;
    }
}

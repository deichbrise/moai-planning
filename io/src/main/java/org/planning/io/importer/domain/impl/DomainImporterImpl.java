package org.planning.io.importer.domain.impl;

import org.planning.io.importer.AbstractImporter;
import org.planning.io.importer.domain.DomainImporter;
import org.planning.io.model.DomainImportResult;
import org.planning.io.service.DayOfWeekMapper;
import org.planning.io.service.DomainModelClassMapper;
import org.planning.persistence.model.*;
import org.planning.persistence.model.constraint.BinaryConstraint;
import org.planning.persistence.model.constraint.Constraint;
import org.planning.persistence.model.constraint.LimitConstraint;
import org.planning.persistence.model.constraint.UniqueConstraint;
import org.planning.util.GuidGenerator;
import org.planning.util.condition.Conditions;
import org.planning.util.exception.PlanningRuntimeException;
import org.planning.util.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pascalstammer
 */
public class DomainImporterImpl extends AbstractImporter<DomainImportResult> implements DomainImporter {

    private DayOfWeekMapper dayOfWeekMapper;

    private DomainModelClassMapper domainModelClassMapper;

    @Override
    protected DomainImportResult processImport(File file) {
        final DomainImportResult domainImportResult = new DomainImportResult();

        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            final List<Lecture> lectures = parseLectures(document);
            final List<Day> days = parseDays(document);
            final List<TimeSlot> timeSlots = parseTimeSlots(document);
            final List<Instructor> instructors = parseInstructors(document);
            final List<Room> rooms = parseRooms(document);

            domainImportResult.setLectures(lectures);
            domainImportResult.setInstructors(instructors);
            domainImportResult.setDays(days);
            domainImportResult.setRooms(rooms);
            domainImportResult.setTimeSlots(timeSlots);

            final List<Constraint> constraints = parseConstraint(document, domainImportResult);

            domainImportResult.setConstraints(constraints);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return domainImportResult;
    }

    protected List<Lecture> parseLectures(final Document document) {
        final NodeList lectures = document.getElementsByTagName(Nodes.LECTURE);
        final List<Lecture> result = new ArrayList<>();
        for(int i = 0; i < lectures.getLength(); i++) {
            final Node node = lectures.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                final Lecture lecture = new Lecture();
                lecture.setGuid(GuidGenerator.generateGuid());
                lecture.setName(element.getAttribute("name"));
                result.add(lecture);
            }
        }
        return result;
    }

    protected List<Day> parseDays(final Document document) {
        final NodeList days = document.getElementsByTagName(Nodes.DAY);
        final List<Day> result = new ArrayList<>();
        for(int i = 0; i < days.getLength(); i++) {
            final Node node = days.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                final Day day = new Day();
                day.setGuid(GuidGenerator.generateGuid());
                day.setDay(getDayOfWeekMapper().map(element.getAttribute("name")));

                result.add(day);
            }
        }
        return result;
    }

    protected List<TimeSlot> parseTimeSlots(final Document document) {
        final NodeList timeSlots = document.getElementsByTagName(Nodes.TIME_SLOT);
        final List<TimeSlot> result = new ArrayList<>();
        for(int i = 0; i < timeSlots.getLength(); i++) {
            final Node node = timeSlots.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                final TimeSlot timeSlot = new TimeSlot();
                final Time from = Time.valueOf(element.getAttribute("from"));
                final Time to = Time.valueOf(element.getAttribute("to"));
                timeSlot.setGuid(GuidGenerator.generateGuid());
                timeSlot.setInterval(from, to);
                result.add(timeSlot);
            }
        }
        return result;
    }

    protected List<Instructor> parseInstructors(final Document document) {
        final NodeList instructors = document.getElementsByTagName(Nodes.INSTRUCTOR);
        final List<Instructor> result = new ArrayList<>();
        for(int i = 0; i < instructors.getLength(); i++) {
            final Node node = instructors.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                final Instructor instructor = new Instructor();
                instructor.setGuid(GuidGenerator.generateGuid());
                instructor.setName(element.getAttribute("name"));
                result.add(instructor);
            }
        }
        return result;
    }

    protected List<Room> parseRooms(final Document document) {
        final NodeList rooms = document.getElementsByTagName(Nodes.ROOM);
        final List<Room> result = new ArrayList<>();
        for(int i = 0; i < rooms.getLength(); i++) {
            final Node node = rooms.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                final Room room = new Room();
                room.setGuid(GuidGenerator.generateGuid());
                room.setName(element.getAttribute("name"));
                result.add(room);
            }
        }
        return result;
    }

    protected List<Constraint> parseConstraint(final Document document, final DomainImportResult domainImportResult) {
        final List<? extends Constraint> binaryConstraints = parseBinaryConstraint(document, domainImportResult);
        final List<? extends Constraint> limitConstraints = parseLimitConstraint(document, domainImportResult);
        final List<? extends Constraint> uniqueConstraints = parseUniqueConstraint(document, domainImportResult);

        final List<Constraint> constraints = new ArrayList<>();
        constraints.addAll(binaryConstraints);
        constraints.addAll(limitConstraints);
        constraints.addAll(uniqueConstraints);

        return constraints;
    }

    protected List<BinaryConstraint> parseBinaryConstraint(final Document document, final DomainImportResult domainImportResult) throws PlanningRuntimeException {
        final NodeList nodes = document.getElementsByTagName(Nodes.BINARY_CONSTRAINT);
        final List<BinaryConstraint> result = new ArrayList<>();
        for(int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;

                final String rootName = element.getAttribute("of");
                final String[] relations = StringUtils.splitAndTrim(element.getAttribute("to"), ",");

                final DomainModel root = findByName(rootName, domainImportResult);

                if(root == null) {
                    throw new PlanningRuntimeException("No domain model found for name " + rootName);
                }

                final Set<DomainModel> related = new HashSet<>();
                Class<? extends DomainModel> lastClass = null;
                for(final String name : relations) {
                    final DomainModel relation = findByName(name, domainImportResult);
                    if(relation == null) {
                        throw new PlanningRuntimeException("No domain model found for name " + rootName);
                    }
                    if(lastClass == null) {
                        lastClass = relation.getClass();
                    }

                    if(!Conditions.isOfSameClass(lastClass, relation.getClass())) {
                        throw new PlanningRuntimeException();
                    }

                    related.add(relation);
                }

                result.add(new BinaryConstraint(root, related));
            }
        }
        return result;
    }

    protected List<LimitConstraint> parseLimitConstraint(final Document document, final DomainImportResult domainImportResult) {
        final NodeList nodes = document.getElementsByTagName(Nodes.BINARY_CONSTRAINT);
        final List<LimitConstraint> result = new ArrayList<>();
        for(int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                final Element element = (Element)node;

                final String name = element.getAttribute("of");
                final DomainModel root = findByName(name, domainImportResult);
                if(root == null) {
                    throw new PlanningRuntimeException("No domain model found for name " + name);
                }
                final Integer limit = Integer.valueOf(element.getAttribute("to"));

                result.add(new LimitConstraint(root.getClass(), limit));
            }
        }
        return result;
    }

    protected List<UniqueConstraint> parseUniqueConstraint(final Document document, final DomainImportResult domainImportResult) {
        final NodeList nodes = document.getElementsByTagName(Nodes.UNIQUE_CONSTRAINT);
        final List<UniqueConstraint> result = new ArrayList<>();
        for(int i = 0; i < nodes.getLength(); i++) {
            final Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                final Element element = (Element)node;
                final String root = element.getAttribute("of");
                final String[] byS = StringUtils.splitAndTrim(element.getAttribute("by"), ",");

                final Class<? extends DomainModel> rootClass = getDomainModelClassMapper().map(root);
                if(rootClass == null) {
                    throw new PlanningRuntimeException("No domain model of class " + root + " found.");
                }

                final Set<Class<? extends DomainModel>> byClasses = new HashSet<>();
                for(final String byClassName : byS) {
                    final Class<? extends  DomainModel> by = getDomainModelClassMapper().map(byClassName);
                    if(by == null) {
                        throw new PlanningRuntimeException("No domain model of class " + root + " found.");
                    }
                    byClasses.add(by);
                }

                result.add(new UniqueConstraint(rootClass, byClasses));
            }
        }
        return result;
    }

    protected DomainModel findByName(final String name, final DomainImportResult domainImportResult) {
        for(final Lecture lecture : domainImportResult.getLectures()) {
            if(lecture.getName().equals(name)) {
                return lecture;
            }
        }

        for(final Day day : domainImportResult.getDays()) {
            if(day.getName().equals(name)) {
                return day;
            }
        }

        for(final Instructor instructor : domainImportResult.getInstructors()) {
            if(instructor.getName().equals(name)) {
                return instructor;
            }
        }

        for(final Room room : domainImportResult.getRooms()) {
            if(room.getName().equals(name)) {
                return room;
            }
        }

        for(final TimeSlot timeSlot : domainImportResult.getTimeSlots()) {
            if(timeSlot.getName().equals(name)) {
                return timeSlot;
            }
        }

        return null;
    }

    public DayOfWeekMapper getDayOfWeekMapper() {
        return dayOfWeekMapper;
    }

    public void setDayOfWeekMapper(DayOfWeekMapper dayOfWeekMapper) {
        this.dayOfWeekMapper = dayOfWeekMapper;
    }

    public DomainModelClassMapper getDomainModelClassMapper() {
        return domainModelClassMapper;
    }

    public void setDomainModelClassMapper(DomainModelClassMapper domainModelClassMapper) {
        this.domainModelClassMapper = domainModelClassMapper;
    }

    public interface Nodes {
        public static final String LECTURE = "lecture";
        public static final String DAY = "day";
        public static final String TIME_SLOT = "timeSlot";
        public static final String INSTRUCTOR = "instructor";
        public static final String ROOM = "room";
        public static final String BINARY_CONSTRAINT = "reduceTo";
        public static final String UNIQUE_CONSTRAINT = "unique";
        public static final String LIMIT_CONSTRAINT = "limit";
    }

}

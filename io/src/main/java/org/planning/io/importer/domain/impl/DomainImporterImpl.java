package org.planning.io.importer.domain.impl;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.planning.io.importer.AbstractImporter;
import org.planning.io.importer.domain.DomainImporter;
import org.planning.io.model.DomainImportResult;
import org.planning.persistence.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * @author pascalstammer
 */
public class DomainImporterImpl extends AbstractImporter<DomainImportResult> implements DomainImporter {

    @Override
    protected DomainImportResult processImport(File file) {

        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            final Collection<Lecture> lectures = parseLectures(document);
            final Collection<Day> days = parseDays(document);
            final Collection<TimeSlot> timeSlots = parseTimeSlots(document);
            final Collection<Instructor> instructors = parseInstructors(document);
            final Collection<Room> rooms = parseRooms(document);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    protected Collection<Lecture> parseLectures(final Document document) {
        final NodeList lectures = document.getElementsByTagName(Nodes.LECTURE);
    }

    protected Collection<Day> parseDays(final Document document) {
        final NodeList days = document.getElementsByTagName(Nodes.DAY);
    }

    protected Collection<TimeSlot> parseTimeSlots(final Document document) {
        final NodeList timeSlots = document.getElementsByTagName(Nodes.TIME_SLOT);
    }

    protected Collection<Instructor> parseInstructors(final Document document) {
        final NodeList instructors = document.getElementsByTagName(Nodes.INSTRUCTOR);
    }

    protected Collection<Room> parseRooms(final Document document) {
        final NodeList rooms = document.getElementsByTagName(Nodes.ROOM);
    }



    public interface Nodes {
        public static final String LECTURE = "lecture";
        public static final String DAY = "day";
        public static final String TIME_SLOT = "timeSlot";
        public static final String INSTRUCTOR = "instructor";
        public static final String ROOM = "room";
    }
}

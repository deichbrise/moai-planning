package org.planning.io.importer.domain.impl;

import org.junit.Test;
import org.planning.io.importer.domain.DomainImporter;
import org.planning.io.model.DomainImportResult;
import org.planning.io.test.AbstractIOTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author pascalstammer
 */
public class DomainImporterImplTest extends AbstractIOTest {

    @Autowired
    private DomainImporter domainImporter;

    @Test
    public void testImport() {
        final DomainImportResult domainImportResult = domainImporter.doImport("classpath:/domain/testDomain.xml");

        assertEquals( 4, domainImportResult.getLectures().size() );
        assertEquals( 3, domainImportResult.getTimeSlots().size() );
        assertEquals( 4, domainImportResult.getRooms().size() );
        assertEquals( 3, domainImportResult.getInstructors().size() );
        assertEquals( 5, domainImportResult.getDays().size() );
        assertEquals( 3, domainImportResult.getConstraints().size() );
    }
}

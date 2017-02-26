package org.planning.io.importer.domain.impl;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.planning.io.importer.AbstractImporter;
import org.planning.io.importer.domain.DomainImporter;
import org.planning.io.model.DomainImportResult;

import java.io.File;

/**
 * @author pascalstammer
 */
public class DomainImporterImpl extends AbstractImporter<DomainImportResult> implements DomainImporter {

    @Override
    protected DomainImportResult processImport(File file) {
        final DOMParser domParser = new DOMParser();

        return null;
    }
}

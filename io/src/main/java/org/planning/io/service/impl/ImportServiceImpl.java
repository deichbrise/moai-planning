package org.planning.io.service.impl;

import org.planning.io.importer.domain.DomainImporter;
import org.planning.io.model.DomainImportResult;
import org.planning.io.service.ImportService;
import org.planning.util.exception.PlanningRuntimeException;

/**
 * @author pascalstammer
 * @version 26.02.17.
 */
public class ImportServiceImpl implements ImportService {

    private DomainImporter domainImporter;

    @Override
    public DomainImportResult importDomainFromDescriptionFile( final String pathToFile ) {
        return getDomainImporter().doImport( pathToFile );
    }

    @Override
    public void exportCurrentDomain() {
        throw new PlanningRuntimeException("Export of domain not supported");
    }

    public DomainImporter getDomainImporter() {
        return domainImporter;
    }

    public void setDomainImporter( final DomainImporter domainImporter ) {
        this.domainImporter = domainImporter;
    }
}

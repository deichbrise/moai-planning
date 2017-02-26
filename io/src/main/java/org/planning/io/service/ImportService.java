package org.planning.io.service;

import org.planning.io.model.DomainImportResult;

/**
 * Service interface for accessing domain descriptions in file, trigger the import or export process.
 *
 * @author pascalstammer
 */
public interface ImportService {
    DomainImportResult importDomainFromDescriptionFile(final String pathToFile);
    void exportCurrentDomain();
}

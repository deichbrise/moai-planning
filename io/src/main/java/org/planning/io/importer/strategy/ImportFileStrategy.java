package org.planning.io.importer.strategy;

import org.planning.io.importer.context.ImportContext;

import java.io.File;

/**
 * @author pascalstammer
 * @version 04.02.17.
 */
public interface ImportFileStrategy {

    File getFile( ImportContext importContext);
}

package org.planning.io.importer.strategy;

import java.io.File;

/**
 * @author pascalstammer
 */
public interface ImportFileStrategy {
    File getFile(final String filePath);
}

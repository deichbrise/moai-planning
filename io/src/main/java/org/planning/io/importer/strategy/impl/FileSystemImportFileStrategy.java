package org.planning.io.importer.strategy.impl;

import org.apache.commons.io.FileUtils;
import org.planning.io.importer.strategy.ImportFileStrategy;

import java.io.File;

/**
 * @author pascalstammer
 */
public class FileSystemImportFileStrategy implements ImportFileStrategy {

    @Override
    public File getFile(String filePath) {
        return FileUtils.getFile(filePath);
    }
}

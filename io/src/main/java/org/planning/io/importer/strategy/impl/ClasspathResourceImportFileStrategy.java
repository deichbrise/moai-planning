package org.planning.io.importer.strategy.impl;

import org.apache.commons.io.FileUtils;
import org.planning.io.importer.strategy.ImportFileStrategy;
import org.planning.util.FileUtil;
import org.planning.util.exception.PlanningRuntimeException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author pascalstammer
 */
public class ClasspathResourceImportFileStrategy implements ImportFileStrategy {

    @Override
    public File getFile( final String filePath) {
        final InputStream inputStream = getResourceAsStream(filePath);
        File file;
        if(inputStream != null ) {
            try {
                file = File.createTempFile("planning", ".tmp");
                FileUtils.copyInputStreamToFile( inputStream,  file);
            } catch(final IOException e) {
                throw new PlanningRuntimeException( e );
            }
        } else {
            throw new PlanningRuntimeException("InputFile not found.");
        }
        return file;
    }

    protected InputStream getResourceAsStream(final String path ) {
        return this.getClass().getClassLoader().getResourceAsStream( getFilePath( path ) );
    }

    protected String getFilePath(final String path ) {
        return Paths.get(FileUtil.getClassPathFile(path)).toString();
    }
}

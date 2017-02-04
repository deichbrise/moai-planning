package org.planning.io.importer.strategy.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.planning.io.importer.context.ImportContext;
import org.planning.io.importer.strategy.ImportFileStrategy;
import org.planning.util.exception.PlanningRuntimeException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author pascalstammer
 * @version 04.02.17.
 */
public class ImportResourceFileStrategyImpl implements ImportFileStrategy {

    @Override
    public File getFile( final ImportContext importContext ) {
        final InputStream inputStream = getResourceAsStream( importContext );
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

    protected InputStream getResourceAsStream(final ImportContext importContext ) {
        return this.getClass().getClassLoader().getResourceAsStream( getFilePath( importContext ) );
    }

    protected String getFilePath(final ImportContext importContext ) {
        return Paths.get( importContext.getFolder(), "default.xml" ).toString();
    }
}

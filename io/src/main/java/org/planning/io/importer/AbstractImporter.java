package org.planning.io.importer;

import org.planning.io.importer.context.ImportContext;
import org.planning.io.importer.strategy.ImportFileStrategy;
import org.planning.util.exception.PlanningRuntimeException;

import java.io.File;

/**
 * @author pascalstammer
 * @version 04.02.17.
 */
public abstract class AbstractImporter implements Importer {

    private String mode;
    private String basePath;
    private ImportFileStrategy resourceStrategy;
    private ImportFileStrategy fileSystemStrategy;

    public void doImport() {
        doImport( getFile() );
    }

    protected abstract void doImport(final File file);

    protected abstract ImportContext getImportContext();

    protected File getFile() {
        File file;
        switch(getMode()) {
            case ImportMode.RESOURCES:
                file = getResourceStrategy().getFile( getImportContext() );
                break;
            case ImportMode.FILE:
                file = getFileSystemStrategy().getFile( getImportContext() );
                break;
            default:
                throw new PlanningRuntimeException( new StringBuilder( "The ImportFileStrategy for mode " ).append( getMode() ).append( " cannot be found." ).toString() );
        }
        return file;
    }



    public String getMode() {
        return mode;
    }

    public void setMode( final String mode ) {
        this.mode = mode;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath( final String basePath ) {
        this.basePath = basePath;
    }

    public ImportFileStrategy getResourceStrategy() {
        return resourceStrategy;
    }

    public void setResourceStrategy( final ImportFileStrategy resourceStrategy ) {
        this.resourceStrategy = resourceStrategy;
    }

    public ImportFileStrategy getFileSystemStrategy() {
        return fileSystemStrategy;
    }

    public void setFileSystemStrategy( final ImportFileStrategy fileSystemStrategy ) {
        this.fileSystemStrategy = fileSystemStrategy;
    }
}

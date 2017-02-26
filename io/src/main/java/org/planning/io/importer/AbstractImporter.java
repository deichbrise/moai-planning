package org.planning.io.importer;

import org.planning.io.importer.strategy.ImportFileStrategy;
import org.planning.util.condition.Conditions;

import java.io.File;

/**
 * @author pascalstammer
 */
public abstract class AbstractImporter<R> implements Importer<R> {

    private ImportFileStrategy classpathResourceImportFileStrategy;
    private ImportFileStrategy fileSystemImportFileStrategy;

    @Override
    public R doImport(final String file) {
        final File loadedFile = getFile(file);
        return processImport(loadedFile);
    }

    protected abstract R processImport(final File file);

    protected File getFile(final String file) {
        if(Conditions.isClasspathResource(file)) {
            return getClasspathResourceImportFileStrategy().getFile(file);
        } else {
            return getFileSystemImportFileStrategy().getFile(file);
        }
    }

    public ImportFileStrategy getClasspathResourceImportFileStrategy() {
        return classpathResourceImportFileStrategy;
    }

    public void setClasspathResourceImportFileStrategy(ImportFileStrategy classpathResourceImportFileStrategy) {
        this.classpathResourceImportFileStrategy = classpathResourceImportFileStrategy;
    }

    public ImportFileStrategy getFileSystemImportFileStrategy() {
        return fileSystemImportFileStrategy;
    }

    public void setFileSystemImportFileStrategy(ImportFileStrategy fileSystemImportFileStrategy) {
        this.fileSystemImportFileStrategy = fileSystemImportFileStrategy;
    }
}

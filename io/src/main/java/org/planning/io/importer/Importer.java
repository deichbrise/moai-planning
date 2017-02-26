package org.planning.io.importer;

/**
 * @author pascalstammer
 */
public interface Importer<R> {
    public R doImport(final String file);
}

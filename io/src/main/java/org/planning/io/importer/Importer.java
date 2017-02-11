package org.planning.io.importer;

/**
 * @author pascalstammer
 * @version 04.02.17.
 */
public interface Importer {
    void doImport();

    public interface ImportMode {
        public static final String RESOURCES = "resources";
        public static final String FILE = "file";
    }
}

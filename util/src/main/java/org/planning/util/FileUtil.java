package org.planning.util;

import org.planning.util.condition.Conditions;
import org.planning.util.exception.PlanningRuntimeException;

/**
 * @author pascalstammer
 */
public class FileUtil {

    public static String getClassPathFile(final String path) {
        if(!Conditions.isClasspathResource(path)) {
            throw new PlanningRuntimeException("File path is not a classpath file resource");
        }
        return path.replace("classpath:", "");
    }
}

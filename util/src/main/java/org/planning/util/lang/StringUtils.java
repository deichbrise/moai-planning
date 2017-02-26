package org.planning.util.lang;

/**
 * @author pascalstammer
 */
public class StringUtils {
    public static String[] splitAndTrim(final String s, final String splitBy) {
        final String[] split = s.split(splitBy);
        final String[] result = new String[split.length];

        for(int i = 0; i < result.length; i++) {
            result[i] = split[i].trim();
        }

        return result;
    }
}

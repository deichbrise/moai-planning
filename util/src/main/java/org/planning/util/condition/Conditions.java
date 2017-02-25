package org.planning.util.condition;

/**
 * @author pascalstammer
 */
public class Conditions {

    /**
     * Determine whether class1 equals class2.
     *
     * @param class1 first class
     * @param class2 second class
     * @return true if classes are equal
     */
    public static boolean isOfSameClass(final Class<?> class1, final Class<?> class2) {
        return class1.equals(class2);
    }
}

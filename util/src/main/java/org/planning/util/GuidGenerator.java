package org.planning.util;

import java.util.UUID;

/**
 * @author pascalstammer
 */
public class GuidGenerator {
    public static synchronized String generateGuid() {
        return UUID.randomUUID().toString();
    }
}

package org.planning.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

/**
 * Created by pascalstammer on 23.02.17.
 */
public class BeanUtil {

    /**
     * Determine if bean has any property of class clazz
     * @param bean bean to inspect
     * @param clazz should have a property of this class
     * @return true if bean has property of class clazz
     */
    public static boolean hasPropertyOfClass(Class<?> bean, Class<?> clazz) {
        final PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(bean);
        for(PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if(propertyDescriptor.getPropertyType().equals(clazz)) {
                return true;
            }
        }
        return false;
    }
}

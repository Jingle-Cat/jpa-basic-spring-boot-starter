package com.github.uinios.jpa.basic.service;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jingle-Cat
 */
public class IgnoreProperties {
    private IgnoreProperties() {
    }

    /**
     * get the fields that need to be updated
     *
     * @return take out non empty fields in the target source
     */
    public static String[] ignoreProperties(Object object) {
        //fields that need to be updated
        Set<String> params = new HashSet<>();
        //get the background to set the value of the parameter
        BeanWrapper bean = new BeanWrapperImpl(object);
        //get a description of the property (field)
        PropertyDescriptor[] descriptors = bean.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            //get the value of a field
            Object value = bean.getPropertyValue(descriptor.getName());
            if (Objects.nonNull(value)) {
                params.add(descriptor.getName());
            }
        }
        return params.toArray(new String[0]);
    }

}

package com.ttn.demo.core.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "Class Configuration Service")
public @interface ClassConfiguration {

    @AttributeDefinition(name = "Max Students Allowed")
    int maxStudents() default 30;

    @AttributeDefinition(name = "Passing Marks")
    int passingMarks() default 40;
}

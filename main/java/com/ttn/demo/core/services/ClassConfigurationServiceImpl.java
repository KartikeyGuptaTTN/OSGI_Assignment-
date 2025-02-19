package com.ttn.demo.core.services;


import com.ttn.demo.core.config.ClassConfiguration;
import com.ttn.demo.core.models.Student;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

import java.util.List;

@Component(service = ClassConfigurationService.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ClassConfiguration.class)
public class ClassConfigurationServiceImpl implements ClassConfigurationService {

    private int maxStudents;
    private int passingMarks;

    @Activate
    @Modified
    protected void activate(ClassConfiguration config) {
        this.maxStudents = config.maxStudents();
        this.passingMarks = config.passingMarks();
    }

    @Override
    public boolean isClassLimitReached(List<Student> students) {
        return students.size() >= maxStudents;
    }

    @Override
    public int getPassingMarks() {
        return passingMarks;
    }
}


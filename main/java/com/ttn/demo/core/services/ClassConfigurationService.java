package com.ttn.demo.core.services;

import java.util.List;
import com.ttn.demo.core.models.Student;

public interface ClassConfigurationService {
    boolean isClassLimitReached(List<Student> students);
    int getPassingMarks();
}

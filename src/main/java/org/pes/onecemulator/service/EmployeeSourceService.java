package org.pes.onecemulator.service;

import org.pes.onecemulator.entity.Employee;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.internal.EmployeeSourceModel;

import java.util.Set;

public interface EmployeeSourceService {

    void add(Employee employee, Set<EmployeeSourceModel> sourceModels) throws ValidationException;
}

package com.mindex.challenge.service;

import java.io.IOException;
import java.util.List;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee) throws IOException;
    Employee read(String id);
    Employee update(Employee employee);
    ReportingStructure getReportingStructure(String id);
}

package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee create(Employee employee) throws IOException {
        LOG.debug("Creating employee [{}]", employee);

        
        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);
        
        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    @Override
    public ReportingStructure getReportingStructure(String id) {
    	//DFS code to recursively fetch employee's reporting structure
    	LOG.debug("Creating getReportingStructure employee with id [{}]", id);
    	Employee employee = employeeRepository.findByEmployeeId(id);
    	Stack<Employee> DFS = new Stack<Employee>(); 
		DFS.add(employee);
		ReportingStructure root = new ReportingStructure();
		root.setEmployee(employee);
		int count=0;
		while(DFS.size()!=0) {
			Employee current=DFS.pop();
			if(current.getDirectReports()!=null) {
				count=count+current.getDirectReports().size();
				List<Employee> tempList = new ArrayList<Employee>();
				tempList=current.getDirectReports();
				List<Employee> child = new ArrayList<Employee>();
				for (int x =0 ;x <current.getDirectReports().size();x++){
					Employee emp = employeeRepository.findByEmployeeId(tempList.get(x).getEmployeeId());
					child.add(emp);
					DFS.add(emp);
			}
			current.setDirectReports(child);
			}
		}
		root.setNumberOfReports(count);
		return root;
    }
}


package com.mindex.challenge.service.impl;

import com.mindex.challenge.DataBootstrap;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
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
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private DataBootstrap dbs;
    @Autowired
    private CompensationRepository compensationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private static final String DATASTORE_LOCATION_Compensation = "/Users/tejasraval/Downloads/mindex-java-code-challenge/src/main/resources/static/comp_database.json";

	@Override
    public Compensation create(Compensation employee) throws IOException {
        LOG.debug("Creating compensation [{}]", employee);
        employee.setCompensationId(UUID.randomUUID().toString());
        Employee emp=employeeRepository.findByEmployeeId(employee.employeeId);
        if (emp!=null) {
        	if(compensationRepository.findByEmployeeId(employee.employeeId)!=null) {
        		throw new RuntimeException("Duplicate employeeId in Compensation: " + employee.employeeId);
        	}
        	employee.setEmployee(emp);
        	compensationRepository.insert(employee);
        	FileOutputStream fa   = new FileOutputStream(DATASTORE_LOCATION_Compensation,false);
          dbs.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
          //https://stackoverflow.com/questions/17617370/pretty-printing-json-from-jackson-2-2s-objectmapper
          dbs.objectMapper.writeValue(fa, compensationRepository.findAll());
        }
        else {
        	 throw new RuntimeException("Invalid employeeId: " + employee.employeeId);
        }
        
        return employee;
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Creating compensation with id [{}]", id);

        Compensation employee = compensationRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }
}

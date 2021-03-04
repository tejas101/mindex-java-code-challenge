package com.mindex.challenge.service;

import java.io.IOException;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface CompensationService {
    Compensation create(Compensation employee) throws IOException;
    Compensation read(String id);
}

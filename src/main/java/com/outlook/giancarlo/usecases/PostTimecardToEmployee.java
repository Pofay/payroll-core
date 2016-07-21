/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.HourlyClassification;
import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.Timecard;
import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public class PostTimecardToEmployee {

    private final InMemoryPayrollRepository repository;
    private final LocalDate dateIssued;
    private final int empId;

    public PostTimecardToEmployee(InMemoryPayrollRepository repository, int empId, LocalDate dateIssued) {
        this.repository= repository;
        this.empId = empId;
        this.dateIssued = dateIssued;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        HourlyClassification hc = e.getClassification();
        Timecard t = new Timecard(dateIssued);
        hc.addTimecard(t);
        repository.save(e);
    }
    
}

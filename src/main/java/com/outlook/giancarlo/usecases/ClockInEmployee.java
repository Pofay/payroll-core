/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.HourlyClassification;
import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.TimeSource;
import com.outlook.giancarlo.entities.Timecard;

/**
 *
 * @author pofay
 */
public class ClockInEmployee {

    private final InMemoryPayrollRepository repository;
    private final int empId;
    private final TimeSource timeSource;

    public ClockInEmployee(InMemoryPayrollRepository repository, int empId, TimeSource ts) {
        this.repository = repository;
        this.empId = empId;
        this.timeSource = ts;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        HourlyClassification hc = e.getClassification();
        Timecard t = hc.getTimecardIssuedOn(timeSource.getCurrentDate());
        t.clockIn(timeSource.now());
        repository.save(e);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

/**
 *
 * @author pofay
 */
public class ClockOutEmployee {

    private final TimeSource timeSource;
    private final int empId;
    private final InMemoryPayrollRepository repository;

    public ClockOutEmployee(InMemoryPayrollRepository repository, int empId, TimeSource timeSource) {
        this.repository = repository;
        this.empId = empId;
        this.timeSource = timeSource;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        HourlyClassification hc = e.getClassification();
        Timecard t = hc.getTimecardIssuedOn(timeSource.getCurrentDate());
        t.clockOut(timeSource.now());
        repository.save(e);
    }
    
}

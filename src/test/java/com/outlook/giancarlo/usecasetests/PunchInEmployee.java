/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public class PunchInEmployee {

    private final InMemoryPayrollRepository repository;
    private final int empId;
    private final TimeSource timeSource;

    public PunchInEmployee(InMemoryPayrollRepository repository, int empId, TimeSource ts) {
        this.repository = repository;
        this.empId = empId;
        this.timeSource = ts;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        Timecard t = e.getTimecardIssuedOn(timeSource.getCurrentDate());
        t.punchIn(timeSource);
        repository.save(e);
    }

}

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
public class ChangeToBiweeklySchedule {

    private final int empId;
    private final InMemoryPayrollRepository repo;

    public ChangeToBiweeklySchedule(InMemoryPayrollRepository repo, int empId) {
        this.repo=repo;
        this.empId = empId;
    }

    public void execute() {
        Employee e = repo.getEmployeeById(empId);
        e.setPaymentSchedule(new BiweeklySchedule());
        repo.save(e);
    }
    
}

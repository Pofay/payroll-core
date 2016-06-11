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
public class ChangeEmployeeName {

    private final PayrollRepository repository;
    private final EmployeeName name;
    private final int empId;

    public ChangeEmployeeName(PayrollRepository repository, int empId, EmployeeName name) {
        this.repository = repository;
        this.name = name;
        this.empId = empId;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        e.changeNameTo(name);
        repository.save(e);
    }

}

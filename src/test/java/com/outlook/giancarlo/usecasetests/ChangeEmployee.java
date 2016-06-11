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
public abstract class ChangeEmployee {

    protected final PayrollRepository repository;
    protected final int empId;

    public ChangeEmployee(PayrollRepository repository, int empId) {
        this.repository = repository;
        this.empId = empId;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        change(e);
    }

    protected abstract void change(Employee e);

}

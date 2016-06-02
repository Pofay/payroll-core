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
public class AddEmployeeToDepartment {

    private final PayrollRepository repository;
    private final int deptId;
    private final Employee employee;

    public AddEmployeeToDepartment(PayrollRepository repository, int deptId, Employee e) {
        this.repository = repository;
        this.deptId = deptId;
        this.employee = e;
    }

    void execute() {
        repository.addEmployeeToDepartment(deptId, employee);
    }

}

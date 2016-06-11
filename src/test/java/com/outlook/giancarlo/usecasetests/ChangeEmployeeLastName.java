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
public class ChangeEmployeeLastName {

    private final String lastName;
    private final String firstName;
    private final int deptId;
    private final int empId;
    private final PayrollRepository repository;

    public ChangeEmployeeLastName(PayrollRepository repository, int empId, int deptId, String firstName, String lastName) {
        this.repository = repository;
        this.empId = empId;
        this.deptId = deptId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void execute() {
        throw new UnsupportedOperationException();
        //repository.createNewEmployee(empId, deptId, firstName, lastName);
    }

}

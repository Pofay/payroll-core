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
public class ChangeEmployeeDepartmentId {

    private final PayrollRepository repository;
    private final String lastName;
    private final String firstName;
    private final int deptId;
    private final int empId;

    public ChangeEmployeeDepartmentId(PayrollRepository repository,
            int empId, int changedDeptId, String firstName, String lastName) {
        this.empId = empId;
        this.deptId = changedDeptId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.repository = repository;
    }

    public void execute() {
        repository.createNewEmployee(empId, deptId, firstName, lastName);
    }

}

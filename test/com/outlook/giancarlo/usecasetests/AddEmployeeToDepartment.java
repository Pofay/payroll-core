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
    private final int empId;

    public AddEmployeeToDepartment(PayrollRepository repository, int deptId, int empId) {
        this.repository = repository;
        this.deptId = deptId;
        this.empId = empId;
    }

    void execute() {
        Employee e = repository.getEmployeeById(empId);
        Department d = repository.getDepartmentById(deptId);
        repository.addEmployeeToDepartment(d, e);
    }

}

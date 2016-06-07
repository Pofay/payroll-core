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
public class ChangeEmployeeDepartment implements Usecase {

    private final PayrollRepository repository;
    private final int deptId;
    private final int empId;

    public ChangeEmployeeDepartment(PayrollRepository repository, int deptId, int empId) {
        this.repository = repository;
        this.deptId = deptId;
        this.empId = empId;
    }

    @Override
    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        if (!e.equals(Employee.UNKNOWN)) {
            Department previous = repository.getDepartmentById(e.getDepartmentId());
            Department next = repository.getDepartmentById(deptId);
            previous.remove(e);
            repository.addEmployeeToDepartment(next, e);
        }
    }
}

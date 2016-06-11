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
public class CreateEmployee implements Usecase {

    private final int id;
    private final PayrollRepository repository;
    private final int deptId;
    private final EmployeeName empName;

    public CreateEmployee(PayrollRepository repository, int empId, int deptId, EmployeeName name) {
        if (empId <= 0) {
            throw new IllegalArgumentException("Employee Id should be a positive number".toUpperCase());
        }
        if (deptId <= 0) {
            throw new IllegalArgumentException("Department Id should be a positive number".toUpperCase());
        }
        this.repository = repository;
        this.id = empId;
        this.deptId = deptId;
        this.empName = name;
    }

    @Override
    public void execute() {
        Employee e = new Employee(id, deptId, empName);
        repository.save(e);
    }

}

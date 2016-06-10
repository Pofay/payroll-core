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

    private final String lastName;
    private final String firstName;
    private final int id;
    private final PayrollRepository repository;
    private final int deptId;

    public CreateEmployee(PayrollRepository repository, int empId, String firstName, String lastName, int deptId) {
        if (empId <= 0) {
            throw new IllegalArgumentException("Employee Id should be a positive number".toUpperCase());
        } else {
            this.id = empId;
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.repository = repository;
        this.deptId = deptId;
    }

    @Override
    public void execute() {
        Employee e = new Employee(id, firstName, lastName, deptId);
        repository.add(e);
    }

}

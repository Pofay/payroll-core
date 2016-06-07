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
    private final Department department;
    private PayrollRepository repository;

    public CreateEmployee(PayrollRepository repository, int empId, String firstName, String lastName) {
        if (empId <= 0) {
            throw new IllegalArgumentException("Employee Id should be a positive number".toUpperCase());
        } else {
            this.id = empId;
        }
        int unassignedDepartmentId = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.repository = repository;
        this.department = repository.getDepartmentById(unassignedDepartmentId);
    }

    @Override
    public void execute() {
        Employee e = new Employee(id, firstName, lastName);
        department.addEmployee(e);
        e.setDepartment(department);
        repository.add(e);
    }

}

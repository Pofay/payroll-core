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
public class CreateEmployee {

    private final String lastName;
    private final String firstName;
    private final int id;
    private final Department department;

    public CreateEmployee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = new Department(0, "Unassigned");
    }

    public void execute() {
        Employee e = new Employee(id, firstName, lastName);
        e.setDepartment(department);
        
        PayrollRepository.add(e);
    }

}

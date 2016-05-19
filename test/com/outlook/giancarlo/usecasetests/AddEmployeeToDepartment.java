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

    private final Department department;
    private final EmployeeDetails details;

    public AddEmployeeToDepartment(Department department, EmployeeDetails employeeDetails) {
        this.details = employeeDetails;
        this.department = department;
    }

    public void execute() {
        Employee e = new Employee(details);
        department.add(e);
    }
    
}

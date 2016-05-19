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
public class Employee {

    private final EmployeeDetails details;
    private int departmentId;

    public Employee(EmployeeDetails details) {
        this.details = details;
    }

    public String getFirstName() {
        return details.firstName;
    }

    public String getLastName() {
        return details.lastName;
    }

    public int getId() {
        return details.id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

}

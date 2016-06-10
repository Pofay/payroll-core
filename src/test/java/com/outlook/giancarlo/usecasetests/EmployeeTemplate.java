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
public class EmployeeTemplate {

    public int empId;
    public int deptId;
    public String firstName;
    public String lastName;

    public EmployeeTemplate(int empId, int deptId, String firstName, String lastName) {
        this.empId = empId;
        this.deptId = deptId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

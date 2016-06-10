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

    public static final Employee UNKNOWN = new UnknownEmployee(0, "Unknown", "Unknown", 0);

    private final int id;
    private final String firstName;
    private final String lastName;
    private final int deptId;

    public Employee(int id, String firstName, String lastName, int deptId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptId = deptId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public int getDeptId() {
        return deptId;
    }

    public static class UnknownEmployee extends Employee {

        public UnknownEmployee(int empId, String firstName, String lastName, int deptId) {
            super(empId, firstName, lastName, deptId);
        }
    }
}

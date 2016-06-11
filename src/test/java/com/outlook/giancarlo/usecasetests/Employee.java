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

    public static final Employee UNKNOWN = new UnknownEmployee(0, 0, new EmployeeName("Unknown", "Unknown"));

    private final int id;
    private final int deptId;
    private final EmployeeName name;

    public Employee(int id, int deptId, EmployeeName name) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
    }

    public String getName() {
        return String.format("%s %s", name.first, name.last);
    }

//    public String getLastName() {
//        return lastName;
//    }
    public int getId() {
        return id;
    }

    public int getDeptId() {
        return deptId;
    }

    public static class UnknownEmployee extends Employee {

        public UnknownEmployee(int empId, int deptId, EmployeeName name) {
            super(empId, deptId, name);
        }
    }
}

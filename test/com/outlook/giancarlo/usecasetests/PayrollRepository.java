/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class PayrollRepository {

    private static final Map<Integer, Employee> employees = new HashMap<>();
    private static final Map<Integer, Department> departments = new HashMap<>();

    public static Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    public static Department getDepartment(int deptId) {
        return departments.get(deptId);
    }

    public static void add(Employee e) {
        employees.put(e.getId(), e);
    }

    public static void add(Department d) {
        departments.put(d.getId(), d);
    }

    public static void addEmployeeToDepartment(int deptId, Employee e) {
        Department d = getDepartment(deptId);
        e.setDepartment(d);
    }

}

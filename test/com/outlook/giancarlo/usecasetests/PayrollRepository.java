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

    static Map<Integer, Employee> employees = new HashMap<>();

    public static Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    public static Department getDepartment(int deptId) {
        if (deptId == 1) {
            return new Department(1, "Management");
        } else {
            return new Department(2, "Accounting");
        }
    }

    public static void add(Employee e) {
        employees.put(e.getId(), e);
    }

}

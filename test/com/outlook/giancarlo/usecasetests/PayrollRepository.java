/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class PayrollRepository {

    private static final Map<Integer, Employee> employees = new HashMap<>();
    private static final Map<Integer, Department> departments = new HashMap<>();

    public Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    public Department getDepartment(int deptId) {
        return departments.get(deptId);
    }

    public void add(Employee e) {
        employees.put(e.getId(), e);
    }

    public void add(Department d) {
        departments.put(d.getId(), d);
    }

    public void addEmployeeToDepartment(int deptId, Employee e) {
        Department d = getDepartment(deptId);
        e.setDepartment(d);
    }

    public List<Employee> getAllEmployeesOfDepartment(Department d) {
        List<Employee> queriedEmployees = new ArrayList<>();
        employees
                .values()
                .stream()
                .filter(e -> e.getDepartmentId() == d.getId())
                .forEach(e -> queriedEmployees.add(e));
        return queriedEmployees;
    }

    public static void reset() {
        employees.clear();
        departments.clear();
    }

}

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
public class InMemoryPayrollRepository implements PayrollRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();
    private final Map<Integer, Department> departments = new HashMap<>();

    @Override
    public Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    @Override
    public Department getDepartment(int deptId) {
        return departments.get(deptId);
    }

    @Override
    public void add(Employee e) {
        employees.put(e.getId(), e);
    }

    @Override
    public void add(Department d) {
        departments.put(d.getId(), d);
    }

    @Override
    public void addEmployeeToDepartment(int deptId, Employee e) {
        Department d = getDepartment(deptId);
        d.addEmployee(e);
    }

    @Override
    public List<Employee> getAllEmployeesOfDepartment(int deptId) {
        Department d = getDepartment(deptId);
        return d.getAllEmployees();
    }

    public void reset() {
        employees.clear();
        departments.clear();
    }

}

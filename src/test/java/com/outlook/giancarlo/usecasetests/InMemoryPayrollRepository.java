/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

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

    public InMemoryPayrollRepository() {
        departments.put(0, new Department(0, "Unassigned"));
    }

    @Override
    public Employee getEmployeeById(int empId) {
        if (employees.containsKey(empId)) {
            return employees.get(empId);
        } else {
            return new Employee.UnknownEmployee(empId, "Unknown", "Unknown");
        }
    }

    @Override
    public Department getDepartmentById(int deptId) {
        if (departments.containsKey(deptId)) {
            return departments.get(deptId);
        } else {
            return new Department.UnknownDeparment(deptId, "Unknown");
        }
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
    public void transferEmployeeToDepartment(Department d, Employee e) {
        Department previous = getDepartmentById(e.getDepartmentId());
        previous.remove(e);
        d.addEmployee(e);
    }

    @Override
    public List<Employee> getAllEmployeesWithDepartmentIdOf(int deptId) {
        Department d = getDepartmentById(deptId);
        return d.getAllEmployees();
    }
}

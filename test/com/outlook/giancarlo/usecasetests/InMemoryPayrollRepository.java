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

    @Override
    public Employee getEmployee(int empId) {
        Employee e = employees.get(empId);
        if (e == null) {
            throw new EmployeeDoesNotExistException(
                    String.format("Employee with id of %d does not exist", empId).toUpperCase());
        } else {
            return e;
        }
    }

    @Override
    public Department getDepartment(int deptId) {
        Department d = departments.get(deptId);
        if (d == null) {
            throw new DepartmentDoesNotExistException(
                    String.format("Department with id of %d does not exist", deptId).toUpperCase());
        } else {
            return d;
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
    public void addEmployeeToDepartment(int deptId, Employee e) {
        Department d = getDepartment(deptId);
        d.addEmployee(e);
    }

    @Override
    public List<Employee> getAllEmployeesOfDepartment(int deptId) {
        Department d = getDepartment(deptId);
        return d.getAllEmployees();
    }
}

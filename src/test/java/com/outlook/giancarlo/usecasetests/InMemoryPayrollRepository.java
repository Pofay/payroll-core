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

    public InMemoryPayrollRepository() {
    }

    @Override
    public Employee getEmployeeById(int empId) {
        if (employees.containsKey(empId)) {
            return employees.get(empId);
        } else {
            return Employee.UNKNOWN;
        }
    }

    @Override
    public void add(Employee e) {
        employees.put(e.getId(), e);
    }

    @Override
    public List<Employee> getAllEmployeesWithDepartmentIdOf(int deptId) {
        return null;
  }
}

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
public class InMemoryPayrollRepository {

    private final Map<Integer, Employee> employees = new HashMap<>();

    public InMemoryPayrollRepository() {
    }

    public Employee getEmployeeById(int empId) {
          return employees.containsKey(empId) ? employees.get(empId) : Employee.UNKNOWN; 
    }

    public List<Employee> getAllEmployeesWithDepartmentIdOf(int deptId) {
        List<Employee> employeesOfDeptId = new ArrayList<>();
        employees.values()
                .stream()
                .filter((e) -> (e.getDeptId() == deptId))
                .forEach((e) -> {
                    employeesOfDeptId.add(e);
                });
        return employeesOfDeptId;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public void save(Employee e) {
        employees.put(e.getId(), e);
    }
}

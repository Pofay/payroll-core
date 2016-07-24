/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class InMemoryPayrollRepository implements EmployeeRepository{

    private final Map<Integer, Employee> employees = new HashMap<>();

    public InMemoryPayrollRepository() {
    }

    @Override
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

    @Override
    public void save(Employee e) {
        employees.put(e.getId(), e);
    }

    public void createHourlyWeeklyPaidEmployee(int empId, int deptId, EmployeeName name, double rate) {
        Employee e = new Employee(empId, deptId, name);
        e.setClassification(new HourlyClassification(rate));
        e.setPaymentSchedule(new WeeklySchedule());
        save(e);
    }
}

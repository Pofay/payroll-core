/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.util.List;

/**
 *
 * @author pofay
 */
public interface PayrollRepository {

    void add(Employee e);

    void add(Department d);

    void addEmployeeToDepartment(Department d, Employee e);

    List<Employee> getAllEmployeesOfDepartment(int deptId);

    Department getDepartmentById(int deptId);

    Employee getEmployeeById(int empId);

    public static class EmployeeDoesNotExistException extends RuntimeException {

        public EmployeeDoesNotExistException(String message) {
            super(message);
        }

    }

    public static class DepartmentDoesNotExistException extends RuntimeException {

        public DepartmentDoesNotExistException(String message) {
            super(message);
        }

    }
}

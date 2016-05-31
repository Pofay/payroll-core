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

    void addEmployeeToDepartment(int deptId, Employee e);

    List<Employee> getAllEmployeesOfDepartment(int deptId);

    Department getDepartment(int deptId);

    Employee getEmployee(int empId);

    void reset();
}

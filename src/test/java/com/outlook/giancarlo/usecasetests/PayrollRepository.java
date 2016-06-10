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

    void createEmployee(int id, int deptId, String firstName, String lastName);

    List<Employee> getAllEmployeesWithDepartmentIdOf(int deptId);

    Employee getEmployeeById(int empId);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

/**
 *
 * @author pofay
 */
public interface EmployeeRepository {
 
    Employee getEmployeeById(int empId);
    
    void save(Employee e);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

/**
 *
 * @author pofay
 */
public class EmployeeFactory {
  
    
    public Employee createEmployee(int id, int deptId, EmployeeName name){
        return new Employee(id, deptId, name);
    }
}

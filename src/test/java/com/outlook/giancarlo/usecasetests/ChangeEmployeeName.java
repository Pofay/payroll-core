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
public class ChangeEmployeeName {

    private final PayrollRepository repository;
    private final int empId;
    private final String firstName;
    private final String lastName;
    private final int deptId;

    public ChangeEmployeeName(PayrollRepository repository, int empId, int deptId, String firstName, String lastName) {
        this.repository = repository;
        this.deptId = deptId;
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void execute() {

        throw new UnsupportedOperationException();
//        Employee e =repository.getEmployeeById(empId);
//        
//       
//        
//        repository.save(e);
    }

}

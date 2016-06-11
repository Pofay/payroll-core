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
public class ChangeEmployeeDepartmentId {

    private final PayrollRepository repository;
    private final int deptId;
    private final int empId;

    public ChangeEmployeeDepartmentId(PayrollRepository repository,
            int empId, int changedDeptId) {
        this.empId = empId;
        this.deptId = changedDeptId;
        this.repository = repository;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        e.changeDeptIdTo(deptId);
        repository.save(e);
    }

}

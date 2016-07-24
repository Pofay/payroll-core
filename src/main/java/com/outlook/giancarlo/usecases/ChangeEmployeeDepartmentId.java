/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.EmployeeBuilder;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;

/**
 *
 * @author pofay
 */
public class ChangeEmployeeDepartmentId extends ChangeEmployee {

    private final int deptId;

    public ChangeEmployeeDepartmentId(InMemoryPayrollRepository repository,
            int empId, int changedDeptId) {
        super(repository, empId);
        this.deptId = changedDeptId;
    }

    @Override
    protected Employee change(Employee e) {
        Employee changed = new EmployeeBuilder(e.getId(), deptId, e.getName())
                .build();
        return changed;
    }

}

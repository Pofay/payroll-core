/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.BiweeklySchedule;
import com.outlook.giancarlo.entities.EmployeeBuilder;

/**
 *
 * @author pofay
 */
public class ChangeToBiweeklySchedule {

    private final int empId;
    private final InMemoryPayrollRepository repo;

    public ChangeToBiweeklySchedule(InMemoryPayrollRepository repo, int empId) {
        this.repo = repo;
        this.empId = empId;
    }

    public void execute() {
        Employee e = repo.getEmployeeById(empId);
        EmployeeBuilder builder = new EmployeeBuilder(e.getId(), e.getDeptId(), e.getName());
        Employee changed = builder
                .withPaymentSchedule(new BiweeklySchedule())
                .build();
        repo.save(changed);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.EmployeeRepository;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;

/**
 *
 * @author pofay
 */
public abstract class ChangeEmployee {

    protected final EmployeeRepository repository;
    protected final int empId;

    public ChangeEmployee(EmployeeRepository repository, int empId) {
        this.repository = repository;
        this.empId = empId;
    }

    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        Employee changed = change(e);
        repository.save(changed);
    }

    protected abstract Employee change(Employee e);

}

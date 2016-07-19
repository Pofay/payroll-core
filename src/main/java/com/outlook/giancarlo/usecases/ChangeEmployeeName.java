/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecases;

import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.EmployeeName;

/**
 *
 * @author pofay
 */
public class ChangeEmployeeName extends ChangeEmployee {

    private final EmployeeName name;

    public ChangeEmployeeName(InMemoryPayrollRepository repository, int empId, EmployeeName name) {
        super(repository, empId);
        this.name = name;
    }

    @Override
    protected void change(Employee e) {
        e.changeNameTo(name);
    }
    
    

}

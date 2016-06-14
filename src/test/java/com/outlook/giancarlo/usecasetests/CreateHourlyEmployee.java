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
public class CreateHourlyEmployee {
    
    private final double hourlyRate;
    private final EmployeeName name;
    private final int deptId;
    private final int empId;
    private final InMemoryPayrollRepository repository;
    
    public CreateHourlyEmployee(InMemoryPayrollRepository repository, int empId,
            int deptId, EmployeeName name, double hourlyRate) {
        this.repository = repository;
        this.empId = empId;
        this.deptId = deptId;
        this.name = name;
        this.hourlyRate = hourlyRate;
    }
    
    public void execute() {
        Employee e = new Employee(empId, deptId, name);
        e.setClassification(new HourlyClassification(hourlyRate));
        repository.save(e);
    }
    
}
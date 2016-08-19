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
public class EmployeeBuilder {

    public final EmployeeName name;
    public final int deptId;
    public final int empId;
    public HourlyClassification classification;
    public PaymentSchedule paymentSchedule;

    public EmployeeBuilder(int empId, int deptId, EmployeeName name) {
        this.empId = empId;
        this.deptId = deptId;
        this.name=name;
    }

    public EmployeeBuilder withClassification(HourlyClassification classification) {
        this.classification = classification;
        return this;
    }

    public EmployeeBuilder withPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
        return this;
    }

    public Employee build() {
        Employee e = new Employee(this);
        return e;
    }

    
}

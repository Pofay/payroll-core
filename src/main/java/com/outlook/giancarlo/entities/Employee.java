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
public class Employee {

    // public static final Employee UNKNOWN = new UnknownEmployee(0, 0, new EmployeeName("Unknown", "Unknown"));
    private final int id;
    private final int deptId;
    private final EmployeeName name;
    private final HourlyClassification classfication;
    private final PaymentSchedule schedule;

    /*
    public Employee(int id, int deptId, EmployeeName name) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
    }*/
    public Employee(EmployeeBuilder builder) {
        this.id = builder.empId;
        this.deptId = builder.deptId;
        this.name = builder.name;
        this.classfication = builder.classification;
        this.schedule = builder.paymentSchedule;
    }

    public int getId() {
        return id;
    }

    public HourlyClassification getClassification() {
        return classfication;
    }

    public String getNameInString() {
        return String.format("%s %s", name.first, name.last);
    }

    public EmployeeName getName() {
        return name;
    }

    public int getDeptId() {
        return deptId;
    }

    public PaymentSchedule getPaymentSchedule() {
        return schedule;
    }

    public void Payday(Paycheck pc) {
        double grosspay = classfication.calculateGrossPay(pc);
        pc.grosspay = grosspay;
    }

    /*
    public static class UnknownEmployee extends Employee {

        public UnknownEmployee(int empId, int deptId, EmployeeName name) {
            super(empId, deptId, name);
        }
    }*/
}

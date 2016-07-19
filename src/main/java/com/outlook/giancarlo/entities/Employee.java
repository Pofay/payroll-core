/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public class Employee {

    public static final Employee UNKNOWN = new UnknownEmployee(0, 0, new EmployeeName("Unknown", "Unknown"));

    private final int id;
    private int deptId;
    private EmployeeName name;
    private HourlyClassification classfication;
    private PaymentSchedule schedule;

    public Employee(int id, int deptId, EmployeeName name) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
    }

    public int getId() {
        return id;
    }

    public void setClassification(HourlyClassification classification) {
        this.classfication = classification;
    }

    public HourlyClassification getClassification() {
        return classfication;
    }

    public void changeNameTo(EmployeeName name) {
        this.name = name;
    }

    public String getName() {
        return String.format("%s %s", name.first, name.last);
    }

    public void changeDeptIdTo(int deptId) {
        this.deptId = deptId;
    }

    public int getDeptId() {
        return deptId;
    }

    public PaymentSchedule getPaymentSchedule() {
        return schedule;
    }

    public void setPaymentSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void Payday(Paycheck pc) {
        double grosspay = classfication.calculateGrossPay(pc);
        pc.grosspay = grosspay;
    }

    public static class UnknownEmployee extends Employee {

        public UnknownEmployee(int empId, int deptId, EmployeeName name) {
            super(empId, deptId, name);
        }
    }
}

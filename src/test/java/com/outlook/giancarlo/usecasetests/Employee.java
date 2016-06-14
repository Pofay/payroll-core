/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
    private HashMap<LocalDate, Timecard> timecards;

    public Employee(int id, int deptId, EmployeeName name) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        timecards = new HashMap<>();
    }

    public void setClassification(HourlyClassification classification) {
        this.classfication = classification;
    }

    public String getName() {
        return String.format("%s %s", name.first, name.last);
    }

    public int getId() {
        return id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void changeNameTo(EmployeeName name) {
        this.name = name;
    }

    public void changeDeptIdTo(int deptId) {
        this.deptId = deptId;
    }

    public HourlyClassification getClassification() {
        return classfication;
    }

    public Timecard getTimecardIssuedOn(LocalDate dateIssued) {
        return timecards.get(dateIssued);
    }

    public void addTimecard(Timecard t) {
        timecards.put(t.getDateIssued(),t);
    }

    public static class UnknownEmployee extends Employee {

        public UnknownEmployee(int empId, int deptId, EmployeeName name) {
            super(empId, deptId, name);
        }
    }
}

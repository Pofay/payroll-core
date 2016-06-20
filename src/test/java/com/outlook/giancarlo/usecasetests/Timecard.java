/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author pofay
 */
public class Timecard {

    private static final int LUNCH_BREAK_OFFSET = 1;

    private final LocalDate dateIssued;
    private LocalTime initialTime = LocalTime.of(0, 0);
    private LocalTime endTime = LocalTime.of(0, 0);

    public Timecard(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public LocalTime getClockedInTime() {
        return initialTime;
    }

    public void clockIn(LocalTime initialTime) {
        this.initialTime = initialTime;
    }

    public LocalTime getClockedOutTime() {
        return endTime;
    }

    public void clockOut(LocalTime outTime) {
        this.endTime = outTime;
    }

    public double getTotalHours() {
        double totalHours = 0.0;
        if (endTime.getHour() <= 0 || initialTime.getHour() <= 0) {
            return totalHours;
        }
//        if (initialTime.getHour() <= 0) {
//            return totalHours;
//        }
        totalHours = endTime.getHour() - initialTime.getHour() - LUNCH_BREAK_OFFSET;
        return totalHours;
    }
}

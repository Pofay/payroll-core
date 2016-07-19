/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

import com.outlook.giancarlo.exceptions.UnmetPreconditionException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author pofay
 */
public class Timecard {

    private static final int LUNCH_BREAK_OFFSET = 1;

    private final LocalDate dateIssued;
    private LocalTime initialTime;
    private LocalTime endTime;

    public Timecard(LocalDate dateIssued) {
        if(dateIssued == null)
            throw new UnmetPreconditionException("DateIssued cannot be null".toLowerCase());
        this.dateIssued = dateIssued;
        initialTime = LocalTime.of(0, 0);
        endTime = LocalTime.of(0, 0);
        
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
        totalHours = endTime.getHour() - initialTime.getHour() - LUNCH_BREAK_OFFSET;
        return totalHours;
    }
    
    public boolean isBetween(LocalDate startDate, LocalDate payDate){
        return dateIssued.isEqual(startDate) || dateIssued.isBefore(payDate.plusDays(1));
    }
    
}

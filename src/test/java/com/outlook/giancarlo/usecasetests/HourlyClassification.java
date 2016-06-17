/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

/**
 *
 * @author pofay
 */
public class HourlyClassification {

    private final double rate;
    private final HashMap<LocalDate, Timecard> timecards;

    public HourlyClassification(double hourlyRate) {
        this.rate = hourlyRate;
        timecards = new HashMap<>();
    }

    public double getRate() {
        return rate;
    }

    public Timecard getTimecardIssuedOn(LocalDate dateIssued) {
        return timecards.get(dateIssued);
    }

    public void addTimecard(Timecard t) {
        timecards.put(t.getDateIssued(), t);
    }
}

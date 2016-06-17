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
public class HourlyClassification {

    private final double rate;

    public HourlyClassification(double hourlyRate) {
        this.rate = hourlyRate;
    }

    public double getRate() {
        return rate;
    }

    public Timecard getTimecardIssuedOn(LocalDate dateIssued) {
        Timecard t = new Timecard(dateIssued);
        t.clockIn(LocalTime.of(10,30));
        return t;
    }

}

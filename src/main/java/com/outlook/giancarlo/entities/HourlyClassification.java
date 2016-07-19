/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

import com.outlook.giancarlo.entities.Timecard;
import com.outlook.giancarlo.entities.Paycheck;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public double calculateGrossPay(Paycheck pc) {
        double pay = timecards.values().stream()
                .filter(t -> t.isBetween(pc.startDate, pc.payDate))
                .mapToDouble(t-> t.getTotalHours() * rate)
                .sum();
        
        return pay;
    }
}

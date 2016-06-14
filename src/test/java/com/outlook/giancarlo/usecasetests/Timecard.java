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

    private final LocalDate dateIssued;
    private LocalTime initialTime;

    public Timecard(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public LocalTime getInitialTime() {
        return initialTime;
    }

    public void punchIn(TimeSource timeSource) {
        this.initialTime = timeSource.now();
    }
}

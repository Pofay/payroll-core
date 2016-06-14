/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 *
 * @author pofay
 */
public class TimeSource {

    private final Clock clock;

    public TimeSource(Clock clock) {
        this.clock = clock;
    }

    LocalDate getCurrentDate() {
        return LocalDate.of(2016, Month.JUNE, 15);
    }

    LocalTime now() {
        return LocalTime.now(clock);
    }

}

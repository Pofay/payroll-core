/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author pofay
 */
public class TimeSource {

    private final Clock clock;

    public TimeSource(Clock clock) {
        this.clock = clock;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now(clock);
    }

    public LocalTime now() {
        return LocalTime.now(clock);
    }

}

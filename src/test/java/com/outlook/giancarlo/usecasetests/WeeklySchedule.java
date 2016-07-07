/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public class WeeklySchedule implements PaymentSchedule {

    @Override
    public String toString() {
        return "weekly";
    }

    @Override
    public boolean isPayDate(LocalDate payDate) {
      return payDate.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

}

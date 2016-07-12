/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author pofay
 */

@RunWith(Parameterized.class)
public class WeeklyScheduleTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {LocalDate.of(2016,Month.JULY, 1), true}, 
            {LocalDate.of(2016, Month.JULY, 7), false},
            {LocalDate.of(2016, Month.JULY, 8), true}
        });
    }
    
    private LocalDate date;
    private boolean expected;
    
    public WeeklyScheduleTest(LocalDate payDate, boolean expected) {
        date = payDate;
        this.expected=expected;
    }

    @Test
    public void WeeklyPaymentScheduleChecksifDateIsPayDate() {
        PaymentSchedule schedule = new WeeklySchedule();

        assertThat(schedule.isPayDate(date), is(expected));
    }
    
     @Test
    public void WeeklyPaymentScheduleReturnsPayPeriodStartDate4DaysFromGivenPayDate() {
        PaymentSchedule schedule = new WeeklySchedule();
                    
        LocalDate expectedDate = date.minusDays(4);
        
        assertThat(schedule.getPayPeriodStartDate(date),
                is(expectedDate));
    }
}

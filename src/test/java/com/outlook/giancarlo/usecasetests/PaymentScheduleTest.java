/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author pofay
 */

@RunWith(Parameterized.class)
public class PaymentScheduleTest {

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
    
    public PaymentScheduleTest(LocalDate payDate, boolean expected) {
        date = payDate;
        this.expected=expected;
    }

    @Test
    public void WeeklyPaymentScheduleChecksifDateIsPayDate() {
        PaymentSchedule schedule = new WeeklySchedule();

        assertThat(schedule.isPayDate(date), is(expected));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PaymentScheduleTest {
    
    @Test
    public void WeeklyPaymentScheduleReturnsCorrectResultOnCorrectDate() {
        PaymentSchedule schedule = new WeeklySchedule();
        
        LocalDate payDate = LocalDate.of(2016, Month.JULY, 1);
        
        assertThat(schedule.isPayDate(payDate), is(true));
    }
    
    @Test
    public void WeeklyPaymentScheduleReturnsCorrectOnWrongDate() {
        PaymentSchedule schedule = new WeeklySchedule();
        
        LocalDate payDate = LocalDate.of(2016, Month.JULY, 7);
        
        assertThat(schedule.isPayDate(payDate), is(false));
    }
    
}

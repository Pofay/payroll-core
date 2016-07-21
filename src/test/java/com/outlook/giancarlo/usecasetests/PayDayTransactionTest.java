/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import com.outlook.giancarlo.entities.EmployeeName;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.Paycheck;
import com.outlook.giancarlo.entities.TimeSource;
import com.outlook.giancarlo.usecases.ClockIn;
import com.outlook.giancarlo.usecases.ClockOut;
import com.outlook.giancarlo.usecases.CreateHourlyEmployee;
import com.outlook.giancarlo.usecases.PaydayTransaction;
import com.outlook.giancarlo.usecases.PostTimecard;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author pofay
 */
public class PayDayTransactionTest {

    private static final double DELTA = 0.01;

    @Test
    public void ItShouldNotIncludeTimecardOnPreviousPayPeriodWhenPayingOnAnotherPayPeriod() {
        InMemoryPayrollRepository repo = new InMemoryPayrollRepository();
        EmployeeName name = new EmployeeName("Pofay", "Gilos");
        int empId = 1;
        double hourlyRate = 17.40;
        createHourlyEmployee(repo, empId, name, hourlyRate);
        
        LocalDate previousPayDate = LocalDate.of(2016, Month.JULY, 1);
        LocalDate payDate = LocalDate.of(2016, Month.JULY, 8);

        postTimecard(repo, empId, previousPayDate);
        postTimecard(repo, empId, payDate);

        TimeSource stubSource = mock(TimeSource.class);

        when(stubSource.getCurrentDate()).thenReturn(previousPayDate);
        when(stubSource.now()).thenReturn(LocalTime.of(7, 30));
        ClockIn firstTimecardIn = new ClockIn(repo, empId, stubSource);
        firstTimecardIn.execute();
        when(stubSource.now()).thenReturn(LocalTime.of(16, 30));
        ClockOut firstTimecardOut = new ClockOut(repo, empId, stubSource);
        firstTimecardOut.execute();

        when(stubSource.getCurrentDate()).thenReturn(payDate);
        when(stubSource.now()).thenReturn(LocalTime.of(7, 30));
        ClockIn secondTimecardIn = new ClockIn(repo, empId, stubSource);
        secondTimecardIn.execute();
        when(stubSource.now()).thenReturn(LocalTime.of(16, 30));
        ClockOut secondTimecardOut = new ClockOut(repo, empId, stubSource);
        secondTimecardOut.execute();

        PaydayTransaction payday = new PaydayTransaction(repo, payDate);
        payday.execute();

        Paycheck pc = payday.getPaycheckOf(empId);
        double grosspayInThisPayPeriod = 139.2;

        assertEquals(grosspayInThisPayPeriod, pc.grosspay, DELTA);
    }

    private void createHourlyEmployee(InMemoryPayrollRepository repo, int empId, EmployeeName name, double hourlyRate) {
        CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, 1, name, hourlyRate);
        ce.execute();
    }

    private void postTimecard(InMemoryPayrollRepository repo, int empId, LocalDate previousPayDate) {
        PostTimecard post = new PostTimecard(repo, empId, previousPayDate);
        post.execute();
    }
}

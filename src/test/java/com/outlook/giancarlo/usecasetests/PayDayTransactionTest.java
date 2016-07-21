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
import com.outlook.giancarlo.usecases.AddTimeInEntry;
import com.outlook.giancarlo.usecases.AddTimeOutEntry;
import com.outlook.giancarlo.usecases.CreateHourlyEmployee;
import com.outlook.giancarlo.usecases.PaydayTransaction;
import com.outlook.giancarlo.usecases.PostTimecardToEmployee;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import org.junit.Test;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 *
 * @author pofay
 */
public class PayDayTransactionTest {

    private static final double DELTA = 0.01;
    TimeSource timeSource = mock(TimeSource.class);
    InMemoryPayrollRepository repo = new InMemoryPayrollRepository();

    @Test
    public void ItShouldNotIncludeTimecardOnPreviousPayPeriodWhenPayingOnAnotherPayPeriod() {
        EmployeeName name = new EmployeeName("Pofay", "Gilos");
        int empId = 1;
        int deptId = 1;
        double hourlyRate = 17.40;
        createHourlyEmployee(empId, deptId, name, hourlyRate);

        LocalDate previousPayDate = LocalDate.of(2016, Month.JULY, 1);
        LocalDate payDate = LocalDate.of(2016, Month.JULY, 8);

        postTimecard(empId, previousPayDate);
        postTimecard(empId, payDate);

        addInAndOutEntriesOfTimecardOn(previousPayDate, empId);
        addInAndOutEntriesOfTimecardOn(payDate, empId);

        PaydayTransaction sut = new PaydayTransaction(repo, payDate);
        sut.execute();

        Paycheck pc = sut.getPaycheckOf(empId);
        double grosspayInThisPayPeriod = 139.2;

        assertEquals(grosspayInThisPayPeriod, pc.grosspay, DELTA);
    }

    private void addInAndOutEntriesOfTimecardOn(LocalDate date, int empId) {
        when(timeSource.getCurrentDate()).thenReturn(date);
        when(timeSource.now()).thenReturn(LocalTime.of(7, 30));
        AddTimeInEntry addTimeInEntry = new AddTimeInEntry(repo, empId, timeSource);
        addTimeInEntry.execute();
        when(timeSource.now()).thenReturn(LocalTime.of(16, 30));
        AddTimeOutEntry addTimeOutEntry = new AddTimeOutEntry(repo, empId, timeSource);
        addTimeOutEntry.execute();
    }

    private void createHourlyEmployee(int empId, int deptId, EmployeeName name, double hourlyRate) {
        CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, deptId, name, hourlyRate);
        ce.execute();
    }

    private void postTimecard(int empId, LocalDate previousPayDate) {
        PostTimecardToEmployee post = new PostTimecardToEmployee(repo, empId, previousPayDate);
        post.execute();
    }
}

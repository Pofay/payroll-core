/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author pofay
 */
public class PaydayTransactionTest {

    public static double DELTA = 1.0;
    @Test
    public void ItShouldBeAbleToPayAHourlyEmployeeOnCorrectPayDate() {
        InMemoryPayrollRepository repository = new InMemoryPayrollRepository();
        EmployeeName name = new EmployeeName("Gian Carlo", "Gilos");
        int empId = 2;
        CreateHourlyEmployee che = new CreateHourlyEmployee(repository, 2, 3, name, 15.25);
        che.execute();
        LocalDate paydate = LocalDate.of(2016, Month.JULY, 8);
        PostTimecard postTimecard = new PostTimecard(repository, 2, paydate);
        postTimecard.execute();
        HourlyClassification hc = repository.getEmployeeById(empId).getClassification();
        Timecard timecard = new Timecard(paydate);
        hc.addTimecard(timecard);
        timecard.clockIn(LocalTime.of(7, 30));
        timecard.clockOut(LocalTime.of(16, 30));
        
        PaydayTransaction t = new PaydayTransaction(paydate);
        t.execute();
        
        Paycheck paycheck = t.getPaycheckOf(empId);
        assertEquals(122.0, paycheck.grosspay, DELTA);
    }
}

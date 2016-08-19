/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import com.outlook.giancarlo.usecases.PostTimecardToEmployee;
import com.outlook.giancarlo.usecases.PaydayTransaction;
import com.outlook.giancarlo.usecases.CreateHourlyEmployee;
import com.outlook.giancarlo.entities.HourlyClassification;
import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.EmployeeName;
import com.outlook.giancarlo.entities.Timecard;
import com.outlook.giancarlo.entities.Paycheck;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author pofay
 */
@RunWith(Parameterized.class)
public class PayHourlyEmployeeWIthOneTimecardTest {

    public static double DELTA = 1.0;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {15.25, LocalDate.of(2016, Month.JULY, 8), LocalTime.of(7, 30), LocalTime.of(16, 30), 122.0},
            {16.30, LocalDate.of(2016, Month.JULY, 8), LocalTime.of(6, 30), LocalTime.of(14, 30), 114.1}});
    }

    private double rate;
    private LocalDate payDate;
    private LocalTime clockIn;
    private LocalTime clockOut;
    private double expectedGrossPay;
    InMemoryPayrollRepository repository = new InMemoryPayrollRepository();

    public PayHourlyEmployeeWIthOneTimecardTest(double rate, LocalDate payDate,
            LocalTime clockIn, LocalTime clockOut, double expected) {
        this.rate = rate;
        this.payDate = payDate;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.expectedGrossPay = expected;
    }

    @Test
    public void ItShouldPayAnHourlyEmployeeOnPayDateOneTimecard() {
        int empId = 2;
        createHourlyEmployee(empId, rate);
        postTimecardTo(empId, payDate);
        Timecard timecard = getTimecardOf(empId, payDate);
        timecard.clockIn(clockIn);
        timecard.clockOut(clockOut);

        PaydayTransaction t = new PaydayTransaction(repository, payDate);
        t.execute();

        Paycheck paycheck = t.getPaycheckOf(empId);
        assertEquals(expectedGrossPay, paycheck.grosspay, DELTA);
    }

    private void postTimecardTo(int empId, LocalDate date) {
        PostTimecardToEmployee postTimecard = new PostTimecardToEmployee(repository, empId, date);
        postTimecard.execute();
    }

    private void createHourlyEmployee(int empId, double rate) {
        EmployeeName name = new EmployeeName("Gian Carlo", "Gilos");
        CreateHourlyEmployee che = new CreateHourlyEmployee(repository, empId, 3, name, rate);
        che.execute();
    }

    private Timecard getTimecardOf(int empId, LocalDate payDate) {
        Employee e = repository.getEmployeeById(empId);
        HourlyClassification hc = e.getClassification();
        return hc.getTimecardIssuedOn(payDate);
    }
}

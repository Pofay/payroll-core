/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayingHourlyEmployeeTest {

    @Test
    public void NewlyCreatedHourlyEmployeeShouldHaveAWeeklyPaymentSchedule() {
        InMemoryPayrollRepository repo = new InMemoryPayrollRepository();
        int empId = 1;
        int deptId = 1;
        double stubRate = 1.3;
        EmployeeName name = new EmployeeName("Pofay", "Gilos");

        CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, deptId, name, stubRate);
        ce.execute();

        Employee e = repo.getEmployeeById(empId);
        assertThat(e.getPaymentSchedule(), is(notNullValue()));
        assertThat(e.getPaymentSchedule().toString(), is(equalTo("weekly")));
        assertTrue(e.getPaymentSchedule() instanceof WeeklySchedule);
    }

    @Test
    public void EmployeeWithWeeklyPaymentScheduleCanBeChangedWithBiweeklyPaymentSchedule() {
        InMemoryPayrollRepository repo = new InMemoryPayrollRepository();
        int empId = 1;
        int deptId = 1;
        double stubRate = 1.3;
        EmployeeName name = new EmployeeName("Pofay", "Gilos");

        CreateHourlyEmployee createTransaction = new CreateHourlyEmployee(repo, empId, deptId, name, stubRate);
        createTransaction.execute();

        ChangeToBiweeklySchedule changeTransaction = new ChangeToBiweeklySchedule(repo, empId);
        changeTransaction.execute();

        Employee e = repo.getEmployeeById(empId);
        assertThat(e.getPaymentSchedule().toString(), is(equalTo("biweekly")));
        assertTrue(e.getPaymentSchedule() instanceof BiweeklySchedule);
    }

    @Test
    public void testMethod() {

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import com.outlook.giancarlo.usecases.ChangeToBiweeklySchedule;
import com.outlook.giancarlo.usecases.CreateHourlyEmployee;
import com.outlook.giancarlo.entities.WeeklySchedule;
import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.EmployeeName;
import com.outlook.giancarlo.entities.BiweeklySchedule;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Before;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class CreateHourlyEmployeeTest {

    InMemoryPayrollRepository repo;

    @Before
    public void beforeEach() {
        repo = new InMemoryPayrollRepository();
    }

    @Test
    public void NewlyCreatedHourlyEmployeeShouldHaveAWeeklyPaymentSchedule() {
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
    public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
        int empId = 0;
        int deptId = 3;
        String firstName = "Raul";
        String lastName = "Watson";
        EmployeeName name = new EmployeeName(firstName, lastName);
        double rate = 1.1;

        try {
            CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, deptId, name, rate);
            fail("Should have thrown Exception");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Employee Id should be a positive number";
            assertThat(e.getMessage(), is(expectedMessage));
        }
    }

    @Test
    public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithDeptIdLesserThan1() {
        int empId = 4;
        String firstName = "Ulric";
        String lastName = "Tristan";
        EmployeeName name = new EmployeeName(firstName, lastName);
        int deptId = 0;
        double rate = 1.5;

        try {
            CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, deptId, name, rate);
            fail("Should have thrown Exception");
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Department Id should be a positive number";
            assertThat(e.getMessage(), is(equalTo(expectedMessage)));
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayrollTest {

    InMemoryPayrollRepository repository;

    @Before
    public void beforeEach() {
        repository = new InMemoryPayrollRepository();
    }

    public class EntityCreationContext {

        private final double DELTA = 1.0;

        @Test
        public void ItShouldBeAbleToCreateAnEmployee() {
            final int empId = 1;
            String firstName = "Gian Carlo";
            String lastName = "Gilos";
            EmployeeName name = new EmployeeName(firstName, lastName);
            int deptId = 4;

            executeEmployeeCreation(empId, deptId, name);

            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getName(), is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(empId));
            assertThat(e.getDeptId(), is(equalTo(deptId)));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
            int empId = 0;
            String firstName = "Raul";
            String lastName = "Watson";
            EmployeeName name = new EmployeeName(firstName, lastName);
            int deptId = 3;

            try {
                CreateEmployee ce = new CreateEmployee(repository, empId, deptId, name);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Employee Id should be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithDeptIdLesserThan1() {
            int empId = 4;
            String firstName = "Ulric";
            String lastName = "Tristan";
            EmployeeName name = new EmployeeName(firstName, lastName);
            int deptId = 0;

            try {
                CreateEmployee ce = new CreateEmployee(repository, empId, deptId, name);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Department Id should be a positive number";
                assertThat(e.getMessage(), is(equalTo(expectedMessage.toUpperCase())));
            }
        }

        @Test
        public void ItShouldBeAbleToCreateANewHourlyEmployee() {
            int empId = 8;
            int deptId = 6;
            String firstName = "Adrian";
            String lastName = "Williams";
            double hourlyRate = 9.00;
            EmployeeName name = new EmployeeName(firstName, lastName);

            executeCreateHourlyEmployee(empId, deptId, name, hourlyRate);

            Employee e = repository.getEmployeeById(empId);
            HourlyClassification hc = e.getClassification();

            assertNotNull(hc);
            assertEquals(hourlyRate, hc.getRate(), DELTA);
        }
    }

    public class PostATimecardContext {

        int empId = 30;

        @Before
        public void beforeEach() {
            int deptId = 5;
            String firstName = "Cory";
            String lastName = "Williams";
            double hourlyRate = 8.50;
            EmployeeName name = new EmployeeName(firstName, lastName);
            
            executeCreateHourlyEmployee(empId, deptId, name, hourlyRate);
        }

        @Test
        public void ItShouldBeAbleToPostATimecardToAEmployeeOnDateIssued() {
            LocalDate dateIssued = LocalDate.of(2016, Month.JUNE, 14);
            PostTimecard post = new PostTimecard(repository, empId, dateIssued);

            post.execute();

            Employee e = repository.getEmployeeById(empId);
            Timecard t = e.getTimecardIssuedOn(dateIssued);
            assertThat(t, is(notNullValue()));
            assertThat(t.getDateIssued(), is(dateIssued));
        }
    }
    
    public class NullObjectContext {

        @Test
        public void ItShouldReturnAnUnknownEmployeeWhenQueryingForNonExistingEmployeeInRepository() {
            int nonExistingEmpId = 5;
            Employee e = repository.getEmployeeById(nonExistingEmpId);

            assertNotNull(e);
            assertThat(e.getId(), is(0));
            assertThat(e.getName(), is("Unknown Unknown"));
            assertThat(e.getDeptId(), is(0));
        }
    }

    public class QueryEmployees {

        int empId1 = 2;
        int empId2 = 4;
        int empId3 = 5;
        int deptId1 = 3;
        int deptId2 = 2;

        @Before
        public void beforeEach() {
            executeEmployeeCreation(empId1, deptId1, new EmployeeName("Gian Carlo", "Gilos"));
            executeEmployeeCreation(empId2, deptId1, new EmployeeName("Raul", "Watson"));
            executeEmployeeCreation(empId3, deptId2, new EmployeeName("Ulric", "Tristan"));
        }

        @Test
        public void ItShouldBeAbleToGetAllEmployeesWithASpecificDepartmentId() {
            Employee e1 = repository.getEmployeeById(empId1);
            Employee e2 = repository.getEmployeeById(empId2);
            List<Employee> expected = Arrays.asList(e1, e2);

            List<Employee> employees = repository.getAllEmployeesWithDepartmentIdOf(deptId1);
            assertThat(employees, is(equalTo(expected)));
        }

        @Test
        public void ItShouldBeAbleToGetAllEmployeesWithAnotherDepartmentId() {
            Employee e1 = repository.getEmployeeById(empId3);
            List<Employee> expected = Arrays.asList(e1);

            List<Employee> employees = repository.getAllEmployeesWithDepartmentIdOf(deptId2);
            assertThat(employees, is(equalTo(expected)));
        }

        @Test
        public void ItShouldBeAbleToGetAllEmployees() {
            Employee e1 = repository.getEmployeeById(empId1);
            Employee e2 = repository.getEmployeeById(empId2);
            Employee e3 = repository.getEmployeeById(empId3);
            List<Employee> expected = new ArrayList<>();
            expected.add(e1);
            expected.add(e2);
            expected.add(e3);

            List<Employee> employees = repository.getAllEmployees();

            assertThat(employees, is(equalTo(expected)));
        }
    }

    public class ChangeEmployeeContext {

        int empId = 6;
        int deptId = 7;
        String firstName = "Gian Carlo";
        String lastName = "Gilos";

        @Before
        public void beforeEach() {
            executeEmployeeCreation(empId, deptId, new EmployeeName(firstName, lastName));
        }

        @Test
        public void ItShouldBeAbleToChangeTheFirstNameOfAnEmployee() {
            String changeFirstName = "Pofay";
            ChangeEmployee ce = new ChangeEmployeeName(repository, empId,
                    new EmployeeName(changeFirstName, lastName));

            ce.execute();

            String expected = "Pofay Gilos";
            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getName(), is(equalTo(expected)));
        }

        @Test
        public void ItShouldBeAbleToChangeTheDepartmentIdOfAnEmployee() {
            executeEmployeeCreation(empId, deptId, new EmployeeName(firstName, lastName));

            int changedDeptId = 9;
            ChangeEmployee ce = new ChangeEmployeeDepartmentId(repository,
                    empId, changedDeptId);

            ce.execute();

            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getDeptId(), is(equalTo(changedDeptId)));
        }

        @Test
        public void ItShouldBeAbleToChangeTheLastNameOfAnEmployee() {
            executeEmployeeCreation(empId, deptId, new EmployeeName(firstName, lastName));

            String changedLastName = "Tumulak";
            ChangeEmployee ce = new ChangeEmployeeName(repository, empId,
                    new EmployeeName(firstName, changedLastName));

            ce.execute();

            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getName(), is("Gian Carlo Tumulak"));
        }

    }

    private void executeEmployeeCreation(final int empId, int deptId, EmployeeName name) {
        CreateEmployee ce = new CreateEmployee(repository, empId, deptId, name);
        ce.execute();
    }

    private void executeCreateHourlyEmployee(int empId, int deptId, EmployeeName name, double hourlyRate) {
        CreateHourlyEmployee cnhe = new CreateHourlyEmployee(repository, empId, deptId, name, hourlyRate);
        cnhe.execute();
    }
}

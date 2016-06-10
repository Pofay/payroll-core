/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayrollTest {

    PayrollRepository repository;

    @Before
    public void beforeEach() {
        repository = new InMemoryPayrollRepository();
    }

    public class EntityCreationContext {

        @Test
        public void ItShouldBeAbleToCreateAnEmployee() {
            final int empId = 1;
            String firstName = "Gian Carlo";
            String lastName = "Gilos";
            int deptId = 4;

            executeEmployeeCreation(empId, deptId, firstName, lastName);

            Employee e = repository.getEmployeeById(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(empId));
            assertThat(e.getDeptId(), is(equalTo(deptId)));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
            int empId = 0;
            String firstName = "Raul";
            String lastName = "Watson";
            int deptId = 3;

            try {
                CreateEmployee ce = new CreateEmployee(repository, empId, deptId, firstName, lastName);
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
            int deptId = 0;

            try {
                CreateEmployee ce = new CreateEmployee(repository, empId, deptId, firstName, lastName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Department Id should be a positive number";
                assertThat(e.getMessage(), is(equalTo(expectedMessage.toUpperCase())));
            }
        }

        public class NullObjectContext {

            @Test
            public void ItShouldReturnAnUnknownEmployeeWhenQueryingForNonExistingEmployeeInRepository() {
                int nonExistingEmpId = 5;
                Employee e = repository.getEmployeeById(nonExistingEmpId);

                assertNotNull(e);
                assertThat(e.getId(), is(0));
                assertThat(e.getFirstName(), is("Unknown"));
                assertThat(e.getLastName(), is("Unknown"));
                assertThat(e.getDeptId(), is(0));
            }
        }

        public class QueryEmployeeByDepartmentId {

            int empId1 = 2;
            int empId2 = 4;
            int empId3 = 5;
            int deptId1 = 3;
            int deptId2 = 2;

            @Before
            public void beforeEach() {
                executeEmployeeCreation(empId1, deptId1, "Gian Carlo", "Gilos");
                executeEmployeeCreation(empId2, deptId1, "Raul", "Watson");
                executeEmployeeCreation(empId3, deptId2, "Ulric", "Tristan");
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
        }

        private void executeEmployeeCreation(final int empId, int deptId, String firstName, String lastName) {
            CreateEmployee ce = new CreateEmployee(repository, empId, deptId, firstName, lastName);
            ce.execute();
        }
    }
}

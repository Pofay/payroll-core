/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
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

            executeEmployeeCreation(empId, firstName, lastName);

            Employee e = repository.getEmployeeById(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(empId));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
            int empId = 0;
            String firstName = "Raul";
            String lastName = "Watson";

            try {
                CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Employee Id should be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }

        }

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

        private void executeEmployeeCreation(final int empId, String firstName, String lastName) {
            CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);
            ce.execute();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Assert;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Ignore;

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
            CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);

            ce.execute();

            Employee e = repository.getEmployee(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(empId));
        }

        @Test
        public void ACreatedEmployeeShouldHaveADefaultUnassignedDepartment() {
            int empId = 2;
            String firstName = "John";
            String lastName = "Imperial";
            CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);

            ce.execute();

            int expectedDeptId = 1;
            String expectedDepartmentName = "Unassigned";
            Employee e = repository.getEmployee(empId);
            assertThat(e.getDepartmentName(), is(expectedDepartmentName));
            assertThat(e.getDepartmentId(), is(expectedDeptId));
        }

        @Test
        public void ItShouldBeAbleToCreateANewDepartment() {
            int deptId = 3;
            String deptName = "Accounting";
            CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);

            cd.execute();

            Department department = repository.getDepartment(deptId);
            String expectedDepartmentName = "Accounting";
            assertThat(department.getName(), is(expectedDepartmentName));
            assertThat(department.getId(), is(deptId));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
            try {
                int empId = 0;
                String firstName = "Raul";
                String lastName = "Watson";
                CreateEmployee ce1 = new CreateEmployee(repository, empId, firstName, lastName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Employee Id should be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingADepartmentWithAnIdLesserThan1() {
            try {
                int deptId = 0;
                String deptName = "Computer Science";
                CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Department Id must be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }
        }

        public class AddEmployeeToDepartment {

            int empId = 1;
            int deptId = 2;

            @Before
            public void setup() {
                CreateDepartment cd = new CreateDepartment(repository, deptId, "Management");
                cd.execute();
                CreateEmployee ce = new CreateEmployee(repository, empId, "Gian Carlo", "Gilos");
                ce.execute();
            }

            @Test
            public void ItShouldBeAbleToAddAEmployeeToASpecificDepartment() {
                Employee e = repository.getEmployee(empId);

                repository.addEmployeeToDepartment(deptId, e);

                String expectedDepartmentName = "Management";
                assertThat(e.getDepartmentId(), is(deptId));
                assertThat(e.getDepartmentName(), is(expectedDepartmentName));
            }
        }

        @Test
        public void ItShouldBeAbleToGetTheEmployeesOfASpecificDepartment() {
            int deptId = 2;
            String deptName = "Computer Science";
            CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);

            CreateEmployee usecase1 = new CreateEmployee(repository, 5, "Happah", "Brown");
            CreateEmployee usecase2 = new CreateEmployee(repository, 6, "Ferrer", "Rallat");
            CreateEmployee usecase3 = new CreateEmployee(repository, 7, "Matthew", "Cooper");

            cd.execute();
            usecase1.execute();
            usecase2.execute();
            usecase3.execute();

            Employee e1 = repository.getEmployee(7);
            repository.addEmployeeToDepartment(deptId, e1);

            List<Employee> actualList = repository.getAllEmployeesOfDepartment(deptId);

            assertThat(actualList.size(), is(1));
        }

        
        @Test
        public void ItShouldThrowAnExceptionWhenAddingAnEmployeeToANonExistingDepartment() {
            int deptId = 4;
            int empId = 2;
            String firstName = "Gian Carlo";
            String lastName = "Gilos";

            CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);

            ce.execute();

            Employee e = repository.getEmployee(empId);

            try {
                repository.addEmployeeToDepartment(deptId, e);
                Assert.fail("Should have Thrown Exception");
            } catch (PayrollRepository.DepartmentDoesNotExistException exception) {
                String expectedExceptionMessage
                        = String.format("Department with id of %d does not exist", deptId);
                assertThat(exception.getMessage(), is(expectedExceptionMessage.toUpperCase()));
            }
        }

        @Test
        public void ItShouldThrowAnExceptionWhenQueryingForEmployeeThatDoesNotExist() {
            int empId = 3;
            String firstName = "Gian Carlo";
            String lastName = "Gilos";
            CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);

            ce.execute();
            try {

                Employee e = repository.getEmployee(0);
                fail("Should have thrown Exception");
            } catch (PayrollRepository.EmployeeDoesNotExistException exception) {
                String expectedExceptionMessage
                        = String.format("Employee with id of %d does not exist", 0);
                assertThat(exception.getMessage(), is(expectedExceptionMessage.toUpperCase()));
            }
        }

    }
}

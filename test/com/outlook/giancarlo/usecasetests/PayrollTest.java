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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
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
        public void ACreatedEmployeeShouldHaveADefaultUnassignedDepartment() {
            int empId = 2;
            String firstName = "John";
            String lastName = "Imperial";

            executeEmployeeCreation(empId, firstName, lastName);

            int expectedDeptId = 1;
            String expectedDepartmentName = "Unassigned";
            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getDepartmentName(), is(expectedDepartmentName));
            assertThat(e.getDepartmentId(), is(expectedDeptId));
        }

        @Test
        public void ItShouldBeAbleToCreateANewDepartment() {
            int deptId = 3;
            String deptName = "Accounting";

            executeDepartmentCreation(deptId, deptName);

            Department department = repository.getDepartmentById(deptId);
            String expectedDepartmentName = "Accounting";
            assertThat(department.getName(), is(expectedDepartmentName));
            assertThat(department.getId(), is(deptId));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingAnEmployeeWithAnIdOflesserThan1() {
            int empId = 0;
            String firstName = "Raul";
            String lastName = "Watson";

            try {
                CreateEmployee ce1 = new CreateEmployee(repository, empId, firstName, lastName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Employee Id should be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }

        }

        @Test
        public void ItShouldThrowAnExceptionWhenCreatingADepartmentWithAnIdLesserThan1() {
            int deptId = 0;
            String deptName = "Computer Science";

            try {
                CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);
                fail("Should have thrown Exception");
            } catch (IllegalArgumentException e) {
                String expectedMessage = "Department Id must be a positive number";
                assertThat(e.getMessage(), is(expectedMessage.toUpperCase()));
            }
        }
    }

    public class AddEmployeeToDepartmentContext {

        int empId = 1;
        int deptId = 2;

        String deptName = "Management";
        String firstName = "Gian Carlo";
        String lastName = "Gilos";

        @Before
        public void setup() {
            executeDepartmentCreation(deptId, deptName);
            executeEmployeeCreation(empId, firstName, lastName);
        }

        @Test
        public void ItShouldBeAbleToAddAEmployeeToASpecificDepartment() {
            AddEmployeeToDepartment aetd = new AddEmployeeToDepartment(repository, deptId, empId);
            aetd.execute();

            Employee e = repository.getEmployeeById(empId);
            String expectedDepartmentName = "Management";
            assertThat(e.getDepartmentId(), is(deptId));
            assertThat(e.getDepartmentName(), is(expectedDepartmentName));
        }

        @Test
        public void ItShouldThrowAnExceptionWhenAddingAnEmployeeToANonExistingDepartment() {
            int deptId = 1;

            try {
                AddEmployeeToDepartment aetd = new AddEmployeeToDepartment(repository, deptId, empId);
                aetd.execute();
                Assert.fail("Should have Thrown Exception");
            } catch (PayrollRepository.DepartmentDoesNotExistException exception) {
                String expectedExceptionMessage
                        = String.format("Department with id of %d does not exist", deptId);
                assertThat(exception.getMessage(), is(expectedExceptionMessage.toUpperCase()));
            }
        }

    }

//    @Test
//    public void ItShouldBeAbleToGetAnEmployeeFromADepartment() {
//        int deptId = 2;
//        String deptName = "Computer Science";
//
//        executeDepartmentCreation(deptId, deptName);
//
//        String empFirstNames[] = {"Happah", "Ferrer", "Matthew"};
//        String empLastNames[] = {"Brown", "Rallat", "Cooper"};
//        int empIds[] = {5, 6, 7};
//
//        for (int i = 0; i < empFirstNames.length; i++) {
//            executeEmployeeCreation(empIds[i], empFirstNames[i], empLastNames[i]);
//        }
//
//        Employee e1 = repository.getEmployee(7);
//        repository.addEmployeeToDepartment(deptId, e1);
//
//        List<Employee> actualList = repository.getAllEmployeesOfDepartment(deptId);
//
//        assertThat(actualList.size(), is(1));
//    }
//    @Ignore
//    @Test
//    public void ItShouldThrowAnExceptionWhenQueryingForEmployeeThatDoesNotExist() {
//        int empId = 3;
//        String firstName = "Gian Carlo";
//        String lastName = "Gilos";
//
//        executeEmployeeCreation(empId, firstName, lastName);
//        try {
//
//            Employee e = repository.getEmployee(0);
//            fail("Should have thrown Exception");
//        } catch (PayrollRepository.EmployeeDoesNotExistException exception) {
//            String expectedExceptionMessage
//                    = String.format("Employee with id of %d does not exist", 0);
//            assertThat(exception.getMessage(), is(expectedExceptionMessage.toUpperCase()));
//        }
//    }
    private void executeEmployeeCreation(final int empId, String firstName, String lastName) {
        CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);
        ce.execute();
    }

    private void executeDepartmentCreation(int deptId, String deptName) {
        CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);
        cd.execute();
    }
}

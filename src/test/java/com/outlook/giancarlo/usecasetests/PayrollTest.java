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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
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
        public void ACreatedEmployeeShouldHaveADefaultUnassignedDepartment() {
            int empId = 2;
            String firstName = "John";
            String lastName = "Imperial";

            executeEmployeeCreation(empId, firstName, lastName);

            int expectedDeptId = 0;
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
                CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);
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
            executeAddEmployeeToDepartment(deptId, empId);

            Employee e = repository.getEmployeeById(empId);
            String expectedDepartmentName = "Management";
            assertThat(e.getDepartmentId(), is(deptId));
            assertThat(e.getDepartmentName(), is(expectedDepartmentName));
        }

    }

    public class FilteringEmployeeByDepartmentContext {

        int deptId = 2;
        int idOfLastEmp = 7;
        String empFirstNames[] = {"Happah", "Ferrer", "Matthew"};
        String empLastNames[] = {"Brown", "Rallat", "Cooper"};

        @Before
        public void beforeEach() {
            String deptName = "Computer Science";

            executeDepartmentCreation(deptId, deptName);

            int empIds[] = {5, 6, 7};

            for (int i = 0; i < empFirstNames.length; i++) {
                executeEmployeeCreation(empIds[i], empFirstNames[i], empLastNames[i]);
            }

            executeAddEmployeeToDepartment(deptId, idOfLastEmp);
        }

        @Test
        public void ItShouldBeAbleToFilterAnEmployeeByItsDepartment() {
            int first = 0;
            List<Employee> actualList = repository.getAllEmployeesWithDepartmentIdOf(deptId);
            Employee actualEmp = actualList.get(first);

            Employee expected = repository.getEmployeeById(idOfLastEmp);
            assertThat(actualList.size(), is(1));
            assertThat(actualEmp, is(expected));
        }

        @Test
        public void ItShouldBeAbleToFilterAllEmployeesThatBelongToTheUnassignedDepartment() {
            int unassignedDeptId = 0;
            List<Employee> employees = repository.getAllEmployeesWithDepartmentIdOf(unassignedDeptId);

            assertThat(employees.size(), is(2));
        }
    }

    public class NullObjectContext {

        @Test
        public void ItDoesNothingWhenTransferringAnEmployeeToANonExistingDepartment() {
            int empId = 2;
            String lastName = "Gilos";
            String firstName = "Jacob";
            int unknownDeptId = 9;

            executeEmployeeCreation(empId, firstName, lastName);
            executeAddEmployeeToDepartment(unknownDeptId, empId);

            Employee e = repository.getEmployeeById(empId);
            assertThat(e.getDepartmentName(), is("Unassigned"));
            assertThat(e.getDepartmentId(), is(0));
        }

        @Test
        public void ItShouldReturnAnUnknownEmployeeWhenQueryingForNonExistingEmployeeInRepository() {
            int nonExistingEmpId = 5;
            Employee e = repository.getEmployeeById(nonExistingEmpId);

            assertNotNull(e);
            assertThat(e.getId(), is(0));
            assertThat(e.getFirstName(), is("Unknown"));
            assertThat(e.getLastName(), is("Unknown"));
            assertThat(e.getDepartmentName(), is("Unassigned"));
            assertThat(e.getDepartmentId(), is(0));
        }

        @Test
        public void ItDoesNotAddAnUnknownEmployeeToAnExistingDepartment() {
            int deptId = 10;
            int nonExistingEmpId = 40;
            
            executeDepartmentCreation(deptId, "SomeDepartment");
            executeAddEmployeeToDepartment(deptId, nonExistingEmpId);
            
            List<Employee> employees = repository.getAllEmployeesWithDepartmentIdOf(deptId);

            assertThat(employees.size(), is(0));
        }
    }

    private void executeAddEmployeeToDepartment(int deptId, int empId) {
        TransferEmployeeToDepartment aetd = new TransferEmployeeToDepartment(repository, deptId, empId);
        aetd.execute();
    }

    private void executeEmployeeCreation(final int empId, String firstName, String lastName) {
        CreateEmployee ce = new CreateEmployee(repository, empId, firstName, lastName);
        ce.execute();
    }

    private void executeDepartmentCreation(int deptId, String deptName) {
        CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);
        cd.execute();
    }
}

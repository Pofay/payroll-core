/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @After
    public void afterEach() {
        repository.reset();
    }

    public class EntityCreationContext {

        @Test
        public void ItShouldBeAbleToCreateAnEmployee() {
            final int empId = 1;
            CreateEmployee ce = new CreateEmployee(repository, empId, "Gian Carlo", "Gilos");

            ce.execute();

            Employee e = repository.getEmployee(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(1));
        }

        @Test
        public void ACreatedEmployeeShouldHaveADefaultDepartment() {
            CreateEmployee ce = new CreateEmployee(repository, 2, "John", "Imperial");

            ce.execute();

            Employee e = repository.getEmployee(2);
            assertThat(e.getDepartmentName(), is("Unassigned"));
            assertThat(e.getDepartmentId(), is(1));
        }

        @Test
        public void ItShouldBeAbleToCreateANewDepartment() {
            int deptId = 3;
            String deptName = "Accounting";
            CreateDepartment cd = new CreateDepartment(repository, deptId, deptName);

            cd.execute();

            Department department = repository.getDepartment(deptId);
            assertThat(department.getName(), is("Accounting"));
            assertThat(department.getId(), is(3));
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

            assertThat(e.getDepartmentId(), is(2));
            assertThat(e.getDepartmentName(), is("Management"));
        }
    }

    @Test
    public void ItShouldBeAbleToGetTheEmployeesOfASpecificDepartment() {
        int deptId = 2;
        CreateDepartment cd = new CreateDepartment(repository, deptId, "Computer Science");

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
}

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
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayrollTest {

    public class EntityCreationContext {

        @Test
        public void ItShouldBeAbleToCreateAnEmployee() {
            final int empId = 1;
            CreateEmployee ce = new CreateEmployee(empId, "Gian Carlo", "Gilos");

            ce.execute();

            Employee e = PayrollRepository.getEmployee(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Gian Carlo Gilos"));
            assertThat(e.getId(), is(1));
        }

        @Test
        public void ACreatedEmployeeShouldHaveADefaultDepartment() {
            CreateEmployee ce = new CreateEmployee(2, "John", "Imperial");

            ce.execute();

            Employee e = PayrollRepository.getEmployee(2);
            assertThat(e.getDepartmentName(), is("Unassigned"));
            assertThat(e.getDepartmentId(), is(1));
        }

        @Test
        public void ItShouldBeAbleToCreateANewDepartment() {
            int deptId = 3;
            String deptName = "Accounting";
            CreateDepartment cd = new CreateDepartment(deptId, deptName);

            cd.execute();

            Department department = PayrollRepository.getDepartment(deptId);
            assertThat(department.getName(), is("Accounting"));
            assertThat(department.getId(), is(3));
        }

    }

    public class AddEmployeeToDepartment {

        int empId = 1;
        int deptId = 2;

        @Before
        public void setup() {
            CreateDepartment cd = new CreateDepartment(deptId, "Management");
            cd.execute();
            CreateEmployee ce = new CreateEmployee(empId, "Gian Carlo", "Gilos");
            ce.execute();
        }

        @Test
        public void ItShouldBeAbleToAddAEmployeeToASpecificDepartment() {
            Employee e = PayrollRepository.getEmployee(empId);

            PayrollRepository.addEmployeeToDepartment(deptId, e);

            assertThat(e.getDepartmentId(), is(2));
            assertThat(e.getDepartmentName(), is("Management"));
        }
    }

    @Ignore
    @Test
    public void ItShouldBeAbleToGetAllEmployeesThatAreUnassgined() {
        int deptId = 2;
        CreateDepartment cd = new CreateDepartment(deptId, "Computer Science");

        CreateEmployee usecase1 = new CreateEmployee(5, "Happah", "Brown");
        CreateEmployee usecase2 = new CreateEmployee(6, "Ferrer", "Rallat");
        CreateEmployee usecase3 = new CreateEmployee(7, "Matthew", "Cooper");

        cd.execute();
        usecase1.execute();
        usecase2.execute();
        usecase3.execute();

        Employee e1 = PayrollRepository.getEmployee(7);
        PayrollRepository.addEmployeeToDepartment(deptId, e1);

        Department d = PayrollRepository.getDepartment(deptId);

        Employee e2 = PayrollRepository.getEmployee(5);
        Employee e3 = PayrollRepository.getEmployee(6);
        List<Employee> expectedList = Arrays.asList(e2, e3);

        List<Employee> actualList = PayrollRepository.getAllEmployeesOfDepartment(d);

        assertThat(actualList.size(), is(expectedList.size()));
    }

   
    @Test
    public void ItShouldBeAbleToGetAllEmployeesOfASpecificDepartment() {
        int deptId = 3;
        CreateDepartment cd = new CreateDepartment(deptId, "Accounting");
        cd.execute();

        CreateEmployee usecase1 = new CreateEmployee(2, "Gian Carlo", "Gilos");
        CreateEmployee usecase2 = new CreateEmployee(deptId, "Fernando", "Cejas");
        CreateEmployee usecase3 = new CreateEmployee(4, "Matt", "Brown");

        usecase1.execute();
        usecase2.execute();
        usecase3.execute();

        Employee e1 = PayrollRepository.getEmployee(2);
        Employee e2 = PayrollRepository.getEmployee(deptId);

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(e1);
        expectedEmployees.add(e2);

        PayrollRepository.addEmployeeToDepartment(deptId, e1);
        PayrollRepository.addEmployeeToDepartment(deptId, e2);

        Department d = PayrollRepository.getDepartment(deptId);
        List<Employee> employees = PayrollRepository.getAllEmployeesOfDepartment(d);

        assertThat(employees.size(), is(expectedEmployees.size()));
    }
}

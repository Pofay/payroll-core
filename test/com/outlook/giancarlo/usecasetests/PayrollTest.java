/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.util.ArrayList;
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
        public void ItShouldBeAbleToCreateANewDepartment() {
            int deptId = 1;
            CreateDepartment cd = new CreateDepartment(deptId, "Management");

            cd.execute();

            Department department = PayrollRepository.getDepartment(deptId);
            assertThat(department.getName(), is("Management"));
            assertThat(department.getId(), is(1));
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
    public void ItShouldBeAbleToGetAllEmployeesOfASpecificDepartment() {
        CreateDepartment cd = new CreateDepartment(3, "Accounting");
        cd.execute();

        CreateEmployee usecase1 = new CreateEmployee(2, "Gian Carlo", "Gilos");
        CreateEmployee usecase2 = new CreateEmployee(3, "Fernando", "Cejas");
        CreateEmployee usecase3 = new CreateEmployee(4, "Matt", "Brown");
        
        usecase1.execute();
        usecase2.execute();
        usecase3.execute();

        Employee e1 = PayrollRepository.getEmployee(2);
        Employee e2 = PayrollRepository.getEmployee(3);

        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(e1);
        expectedEmployees.add(e2);
        
        PayrollRepository.addEmployeeToDepartment(3, e1);
        PayrollRepository.addEmployeeToDepartment(3, e2);

        List<Employee> employees = PayrollRepository.getAllEmployeesOfDepartment(3);
        
        assertThat(employees, is(expectedEmployees));
    }
}

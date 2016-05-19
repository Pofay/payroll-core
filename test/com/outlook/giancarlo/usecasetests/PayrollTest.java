/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayrollTest {

    @Test
    public void canCreateANewDepartment() {
        CreateDepartment createDepartment = new CreateDepartment(1, "Accounting");

        createDepartment.execute();

        Department department = DepartmentRepository.get("Accounting");
        assertThat(department.getName(), is("Accounting"));
        assertThat(department.getId(), is(1));
    }

    public class AddingEmployeeToDepartment {

        Department department;

        @Before
        public void ANewlyCreatedDepartment() {
            CreateDepartment createDepartment = new CreateDepartment(2, "Management");

            createDepartment.execute();

            department = DepartmentRepository.get("Management");
        }

        @Test
        public void canAddEmployeesToItself() {
            EmployeeDetails employeeDetails = new EmployeeDetails(1, "Gian Carlo", "Gilos");
            AddEmployeeToDepartment addEmployeeToDepartment
                    = new AddEmployeeToDepartment(department, employeeDetails);

            addEmployeeToDepartment.execute();

            Employee e = department.getEmployee(1);

            String actual = String.format("%s %s", e.getFirstName(), e.getLastName());
            String expected = "Gian Carlo Gilos";
            assertThat(actual, is(expected));
            assertThat(e.getDepartmentId(), is(2));
            assertThat(e.getId(), is(1));
        }
    }

}

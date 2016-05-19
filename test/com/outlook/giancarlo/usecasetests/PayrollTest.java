/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.Before;
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
            final String firstName = "Gian Carlo";
            final String lastName = "Gilos";
            final int empId = 1;

            EmployeeDetails employeeDetails = new EmployeeDetails(empId, firstName, lastName);
            AddEmployeeToDepartment addEmployeeToDepartment
                    = new AddEmployeeToDepartment(department, employeeDetails);

            addEmployeeToDepartment.execute();

            Employee e = department.getEmployee(1);

            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            String expectedName = "Gian Carlo Gilos";
            assertThat(e.getDepartmentId(), is(2));
            assertThat(e.getId(), is(1));
            assertThat(actualName, is(expectedName));
        }
    }

    @Test
    public void canAddMoreEmployeesToADepartment() {
        final int deptId = 16;
        final String deptName = "Engineering";
        CreateDepartment cd = new CreateDepartment(deptId, deptName);
        cd.execute();

        Department department = DepartmentRepository.get(deptName);

        EmployeeDetails firstEmp = new EmployeeDetails(2, "David", "Ytler");
        EmployeeDetails secondEmp = new EmployeeDetails(3, "Sprakak", "Pofay");
        AddEmployeeToDepartment usecase1 = new AddEmployeeToDepartment(department, firstEmp);
        AddEmployeeToDepartment usecase2 = new AddEmployeeToDepartment(department, secondEmp);

        usecase1.execute();
        usecase2.execute();

        List<Employee> employees = department.getAllEmployees();
        assertThat(employees.size(), is(2));
    }

}

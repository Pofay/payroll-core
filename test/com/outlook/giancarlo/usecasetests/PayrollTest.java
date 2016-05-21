/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        public void ItShouldBeAbleToCreateAnotherEmployee() {
            final int empId = 2;
            CreateEmployee ce = new CreateEmployee(empId, "Giovanni", "Bosco");

            ce.execute();

            Employee e = PayrollRepository.getEmployee(empId);
            String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
            assertThat(actualName, is("Giovanni Bosco"));
            assertThat(e.getId(), is(2));
        }

        @Test
        public void ItShouldBeAbleToCreateANewDepartment() {
            CreateDepartment cd = new CreateDepartment(1, "Management");

            cd.execute();

            Department department = PayrollRepository.getDepartment(1);
            assertThat(department.getName(), is("Management"));
            assertThat(department.getId(), is(1));
        }

    }

}

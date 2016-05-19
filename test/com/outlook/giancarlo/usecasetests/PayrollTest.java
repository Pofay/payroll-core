/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

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
    }

    @Test
    public void canCreateASecondDepartment() {
        CreateDepartment createDepartment = new CreateDepartment(1, "Management");

        createDepartment.execute();

        Department department = DepartmentRepository.get("Management");
        assertThat(department.getName(), is("Management"));
    }

    @Test
    public void canCreateANewDepartmentWithDifferentId() {
        CreateDepartment createDepartment = new CreateDepartment(2, "Labor");

        createDepartment.execute();

        Department department = DepartmentRepository.get("Labor");
        assertThat(department.getId(), is(2));
    }

    @Test
    public void canCreateANewDepartmentWithAnIdOf3() {
        CreateDepartment createDepartment = new CreateDepartment(3, "CICCT");

        createDepartment.execute();

        Department department = DepartmentRepository.get("CICCT");
        assertThat(department.getId(), is(3));
    }
    
    

}

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
        assertThat(department.getId(), is(1));
    }
    
    

}

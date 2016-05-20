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

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class PayrollTest {

    @Test
    public void ItShouldBeAbleToCreateAnEmployee() {
        CreateEmployee ce = new CreateEmployee(1,"Gian Carlo", "Gilos");
        
        ce.execute();
        
        Employee e = PayrollRepository.getEmployee(1);
        String actualName = String.format("%s %s", e.getFirstName(), e.getLastName());
        assertThat(actualName, is("Gian Carlo Gilos"));
        assertThat(e.getId(), is(1));
    }
    
}

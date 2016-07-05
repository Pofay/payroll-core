/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
/**
 *
 * @author pofay
 */

@RunWith(HierarchicalContextRunner.class)
public class InteractionTest {

    
    public class CreateEmployeeContext{
        InMemoryPayrollRepository repo = mock(InMemoryPayrollRepository.class);
        
        @Test
        public void CreateEmployeeInteractionTest(){       
            int empId = 2;
            int deptId = 2 ;
            EmployeeName name = new EmployeeName("Pofay", "Gilos");
            
            CreateEmployee ce = new CreateEmployee(repo, empId, deptId, name);
            ce.execute();
            
            verify(repo).createNewEmployeeWith(empId, deptId, name);
        }
        
        
    }
    
}

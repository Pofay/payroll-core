/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import com.outlook.giancarlo.usecases.PostTimecard;
import com.outlook.giancarlo.usecases.CreateHourlyEmployee;
import com.outlook.giancarlo.entities.HourlyClassification;
import com.outlook.giancarlo.entities.Employee;
import com.outlook.giancarlo.entities.InMemoryPayrollRepository;
import com.outlook.giancarlo.entities.EmployeeName;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author pofay
 */
@RunWith(HierarchicalContextRunner.class)
public class InteractionTest {

    public class CreateEmployeeContext {

        InMemoryPayrollRepository repo = mock(InMemoryPayrollRepository.class);

        @Test
        public void CreateHourlyEmployeeInteractionTest() {
            int empId = 2;
            int deptId = 2;
            EmployeeName name = new EmployeeName("Pofay", "Gilos");
            double stubRate = 1.7;

            CreateHourlyEmployee ce = new CreateHourlyEmployee(repo, empId, deptId, name, stubRate);
            ce.execute();

            verify(repo).createHourlyWeeklyPaidEmployee(empId, deptId, name, stubRate);
        }

        @Test
        public void PostTimecardInteractionTest() {
            int empId = 4;
            int deptId = 5;
            double stubRate = 1.0;
            LocalDate stubDate = LocalDate.of(2, Month.MARCH, 14);
            EmployeeName name = new EmployeeName("Gian Carlo", "Gilos");
            Employee stub = new Employee(empId, deptId, name);
            stub.setClassification(new HourlyClassification(stubRate));
            when(repo.getEmployeeById(empId)).thenReturn(stub);
            
            
            PostTimecard pt = new PostTimecard(repo, empId, stubDate);
            pt.execute();
            
            verify(repo).save(stub);
        }

    }

}

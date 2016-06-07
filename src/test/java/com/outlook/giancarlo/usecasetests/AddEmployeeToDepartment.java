/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

/**
 *
 * @author pofay
 */
public class AddEmployeeToDepartment implements Usecase {
    
    private final PayrollRepository repository;
    private final int deptId;
    private final int empId;
    
    public AddEmployeeToDepartment(PayrollRepository repository, int deptId, int empId) {
        this.repository = repository;
        this.deptId = deptId;
        this.empId = empId;
    }
    
    @Override
    public void execute() {
        Employee e = repository.getEmployeeById(empId);
        Department d = repository.getDepartmentById(deptId);
//        if (d == null) {
//            throw new PayrollRepository.DepartmentDoesNotExistException(
//                    String.format("Department with id of %d does not exist", deptId).toUpperCase());
//        } else {
//            repository.transferEmployeeToDepartment(d, e);
//        }
        repository.transferEmployeeToDepartment(d, e);
        
    }
}

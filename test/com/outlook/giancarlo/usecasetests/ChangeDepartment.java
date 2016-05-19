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
class TransferEmployeeToDepartment {

    private final EmployeeDetails details;
    private final Department to;
    private final Department from;

    
    public TransferEmployeeToDepartment
        (Department from, Department to, EmployeeDetails details) {
            this.from = from;
            this.to = to;
            this.details = details;
    }

    public void execute() {
        Employee e = new Employee(details);
        from.remove(details.id);
        to.add(e);
    }
    
}

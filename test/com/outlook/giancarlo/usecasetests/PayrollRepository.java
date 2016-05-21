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
public class PayrollRepository {

    public static Employee getEmployee(int i) {
        if (i == 1) {
            return new Employee(1, "Gian Carlo", "Gilos");
        } else {
            return new Employee(2, "Giovanni", "Bosco");
        }
    }

    public static Department getDepartment(int i) {
        return new Department();
    }

}

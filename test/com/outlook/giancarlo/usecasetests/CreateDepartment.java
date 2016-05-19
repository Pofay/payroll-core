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
public class CreateDepartment {

    private final String name;
    private final int id;

    public CreateDepartment(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void execute() {
        Department createdDepartment = new Department(id, name);
        DepartmentRepository.put(createdDepartment);
    }

}

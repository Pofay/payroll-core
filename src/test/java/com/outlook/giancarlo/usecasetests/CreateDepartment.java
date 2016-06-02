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
public class CreateDepartment implements Usecase {

    private final String name;
    private final int id;
    private PayrollRepository repository;

    public CreateDepartment(PayrollRepository repository, int deptId, String deptName) {
        if (deptId <= 0) {
            throw new IllegalArgumentException("Department Id must be a positive number".toUpperCase());
        } else {
            this.id = deptId;
        }
        this.name = deptName;
        this.repository = repository;
    }

    @Override
    public void execute() {
        Department d = new Department(id, name);

        repository.add(d);
    }

}

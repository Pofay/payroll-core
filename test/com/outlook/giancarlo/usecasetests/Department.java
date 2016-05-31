/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class Department {

    private final String name;
    private final int id;
    private Map<Integer, Employee> employees;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void addEmployee(Employee e) {
        employees.put(e.getId(), e);
        e.setDepartment(this);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }
}

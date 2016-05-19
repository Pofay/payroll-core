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
    private Map<Integer, Employee> employees = new HashMap<>();

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void add(Employee e) {
        e.setDepartmentId(id);
        employees.put(e.getId(), e);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> queriedEmployees = new ArrayList<>(employees.values());
        return queriedEmployees;
    }

    public Employee remove(int id) {
        Employee e = getEmployee(id);
        employees.remove(id);
        return e;
    }
}

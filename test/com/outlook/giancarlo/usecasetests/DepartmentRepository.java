/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class DepartmentRepository {

    public static Map<String, Department> departments = new HashMap<>();

    public static Department get(String name) {
        return departments.get(name);
    }

    public static void put(Department department) {
        departments.put(department.getName(), department);
    }

}

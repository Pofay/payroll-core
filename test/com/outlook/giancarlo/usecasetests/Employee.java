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
public class Employee {

    private final int id;
    private final String firstName;
    private final String lastName;
    private Department department;

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    
    public void setDepartment(Department d){
        this.department = d;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getId(){
        return id;
    }
    
    public int getDepartmentId(){
        return department.getId();
    }
    
    public String getDepartmentName(){
        return department.getName();
    }
    
}

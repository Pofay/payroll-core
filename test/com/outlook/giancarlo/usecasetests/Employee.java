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

    Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName=firstName;
        this.lastName=lastName;
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
        return 2;
    }
    
    public String getDepartmentName(){
        return "Management";
    }
    
}

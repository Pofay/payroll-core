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
public class DepartmentDoesNotExistException extends RuntimeException{

    public DepartmentDoesNotExistException() {
    }

    public DepartmentDoesNotExistException(String message) {
        super(message);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public class BiweeklySchedule implements PaymentSchedule{

    public BiweeklySchedule() {
    }

    @Override
    public String toString() {
        return "biweekly"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPayDate(LocalDate payDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
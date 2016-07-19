/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.entities;

import java.time.LocalDate;

/**
 *
 * @author pofay
 */
public interface PaymentSchedule {
    
   boolean isPayDate(LocalDate payDate);
   LocalDate getPayPeriodStartDate(LocalDate payDate);
}

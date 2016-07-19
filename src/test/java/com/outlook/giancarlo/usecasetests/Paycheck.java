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
public class Paycheck {

    public double grosspay = 122;
    public final LocalDate startDate;
    public final LocalDate payDate;

    public Paycheck(LocalDate startDate, LocalDate payDate) {
        this.startDate = startDate;
        this.payDate = payDate;
    }
}

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
public class Timecard {

    private final LocalDate dateIssued;

    public Timecard(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }
}
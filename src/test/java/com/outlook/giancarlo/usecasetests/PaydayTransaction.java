/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outlook.giancarlo.usecasetests;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pofay
 */
public class PaydayTransaction {

    private final LocalDate paydate;
    private final InMemoryPayrollRepository repo;
    private Map<Integer, Paycheck> paychecks;

    public PaydayTransaction(InMemoryPayrollRepository repository, LocalDate paydate) {
        this.repo = repository;
        this.paydate = paydate;
        this.paychecks = new HashMap<>();
    }

    public void execute() {
        List<Employee> employees = repo.getAllEmployees();

        employees.stream()
                .filter(e -> e.getPaymentSchedule().isPayDate(paydate))
                .forEach(e -> {
                    LocalDate startDate = e.getPaymentSchedule().getPayPeriodStartDate(paydate);
                    Paycheck pc = new Paycheck(startDate, paydate);
                    paychecks.put(e.getId(), pc);
                    e.Payday(pc);
                });

    }

    public Paycheck getPaycheckOf(int empId) {
        return paychecks.get(empId);
    }

}

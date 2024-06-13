package com.lgonzalez.service;

import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;

import java.util.List;

public interface IMonthlyFinanceService extends ICRUDService<MonthlyFinance,Long> {

    List<MonthlyFinance> getMonthlyFinance(Member member, Integer month, Integer year);
}

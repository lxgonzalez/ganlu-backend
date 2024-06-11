package com.lgonzalez.service.impl;

import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.repo.IMonthlyFinanceRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IMonthlyFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyFinanceImpl extends CRUDImpl<MonthlyFinance,Long> implements IMonthlyFinanceService {

    @Autowired
    private IMonthlyFinanceRepo repo;

    @Override
    protected IGenericRepo<MonthlyFinance, Long> getRepo() {
        return repo;
    }


    @Override
    public List<MonthlyFinance> getMonthlyFinance(Member member,Integer month, Integer year) {
        return repo.getMonthlyFinance(member, month, year);
    }
}

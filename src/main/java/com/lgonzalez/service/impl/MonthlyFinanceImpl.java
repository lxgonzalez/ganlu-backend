package com.lgonzalez.service.impl;

import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.repo.IMonthlyFinanceRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IMonthlyFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MonthlyFinanceImpl extends CRUDImpl<MonthlyFinance,Long> implements IMonthlyFinanceService {

    @Autowired
    private IMonthlyFinanceRepo repo;

    @Override
    protected IGenericRepo<MonthlyFinance, Long> getRepo() {
        return repo;
    }


    @Override
    public MonthlyFinance getMonthlyFinance(Member member,Integer month, Integer year) {
        MonthlyFinance monthlyFinance = repo.getMonthlyFinance(member,month,year);

        if(monthlyFinance == null){
            throw new RuntimeException("MonthlyFinance");
        }
        return monthlyFinance;
    }

    @Override
    public void updateTotalMonthlyFinance(MonthlyFinance monthlyFinance) {
        Double totalMonthlyFinance = monthlyFinance.getDistributions().stream().mapToDouble(Distribution::getTotalFinal).sum();
        monthlyFinance.setTotal(totalMonthlyFinance);
        repo.save(monthlyFinance);
    }

    @Override
    public MonthlyFinance save(MonthlyFinance monthlyFinance){
        if(repo.getMonthlyFinance(monthlyFinance.getMember(), monthlyFinance.getMonthFinance(),monthlyFinance.getYearFinance()) != null){
            throw new DataIntegrityViolationException("MonthlyFinance");
        }

        return repo.save(monthlyFinance);
    }

    @Override
    public MonthlyFinance getMonthlyFinance503020(MonthlyFinance monthlyFinance){
        if(monthlyFinance.getDistributions().stream().mapToInt(Distribution:: getPercentage).sum() == 100){
            throw new DataIntegrityViolationException("Percentage");
        }
        return monthlyFinance;
    }
}

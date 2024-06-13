package com.lgonzalez.service.impl;

import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.repo.IDistributionRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionImpl extends CRUDImpl<Distribution,Long> implements IDistributionService {

    @Autowired
    private IDistributionRepo repo;

    @Override
    protected IGenericRepo<Distribution, Long> getRepo() {
        return repo;
    }


    @Override
    public List<Distribution> create503020(Member member, MonthlyFinance monthlyFinance) {
        List<Distribution> distributionList = new ArrayList<>();
        Double total = monthlyFinance.getSalary();

        distributionList.add(new Distribution("Gastos Basicos","Gastos Basicos",50,total*((double) 50/100),total*((double) 50/100),monthlyFinance));
        distributionList.add(new Distribution("Gastos Personales","Gastos Personales",30,total*((double) 30/100),total*((double) 30/100),monthlyFinance));
        distributionList.add(new Distribution("Ahorros","Ahorro a largo plazo",20,total*((double) 20/100),total*((double) 20/100),monthlyFinance));

        return repo.saveAll(distributionList);
    }
}

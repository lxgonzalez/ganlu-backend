package com.lgonzalez.service;

import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;

import java.util.List;

public interface IDistributionService extends ICRUDService<Distribution,Long> {
    List<Distribution> create503020(Member member, MonthlyFinance monthlyFinance);
    
}

package com.lgonzalez.service.impl;

import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.DistributionDetail;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.repo.IDistributionDetailRepo;
import com.lgonzalez.repo.IDistributionRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IDistributionDetailService;
import com.lgonzalez.service.IDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionDetailImpl extends CRUDImpl<DistributionDetail,Long> implements IDistributionDetailService {

    @Autowired
    private IDistributionDetailRepo repo;

    @Override
    protected IGenericRepo<DistributionDetail, Long> getRepo() {
        return repo;
    }

}
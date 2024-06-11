package com.lgonzalez.service.impl;

import com.lgonzalez.model.Category;
import com.lgonzalez.repo.ICatergoryRepo;
import com.lgonzalez.repo.IGenericRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl extends CRUDImpl<Category,Long> {

    @Autowired
    private ICatergoryRepo repo;

    @Override
    protected IGenericRepo<Category, Long> getRepo() {
        return repo;
    }



}

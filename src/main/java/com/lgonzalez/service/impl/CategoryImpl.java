package com.lgonzalez.service.impl;

import com.lgonzalez.model.Category;
import com.lgonzalez.repo.ICategoryRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl extends CRUDImpl<Category,Long> implements ICategoryService {

    @Autowired
    private ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Long> getRepo() {
        return repo;
    }



}

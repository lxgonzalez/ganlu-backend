package com.lgonzalez.service.impl;

import com.lgonzalez.model.Member;
import com.lgonzalez.repo.IMemberRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberImpl extends CRUDImpl<Member,UUID> implements IMemberService {

    @Autowired
    private IMemberRepo repo;

    @Override
    protected IGenericRepo<Member, UUID> getRepo() {
        return repo;
    }

}

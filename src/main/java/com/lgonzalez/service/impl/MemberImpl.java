package com.lgonzalez.service.impl;

import com.lgonzalez.model.Member;
import com.lgonzalez.repo.IMemberRepo;
import com.lgonzalez.repo.IGenericRepo;
import com.lgonzalez.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberImpl extends CRUDImpl<Member,Long> implements IMemberService {

    @Autowired
    private IMemberRepo repo;

    @Override
    protected IGenericRepo<Member, Long> getRepo() {
        return repo;
    }

    @Override
    public Member findById(Long id) {
        Member member = repo.findById(id).orElse(null);
        if(member == null){
            throw new RuntimeException("Member");
        }
        return member;
    }
}

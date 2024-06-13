package com.lgonzalez.repo;

import com.lgonzalez.model.Member;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMemberRepo extends IGenericRepo<Member, UUID>{

}

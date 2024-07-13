package com.lgonzalez.repo;

import com.lgonzalez.model.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepo extends IGenericRepo<Member, Long>{

}

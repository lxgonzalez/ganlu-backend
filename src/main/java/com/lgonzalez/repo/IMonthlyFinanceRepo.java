package com.lgonzalez.repo;

import com.lgonzalez.dto.MonthlyDTO;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMonthlyFinanceRepo extends IGenericRepo<MonthlyFinance,Long>{

    @Query("SELECT m from MonthlyFinance m where m.member = :member and m.monthFinance = :month and m.yearFinance = :year")
    List<MonthlyFinance> getMonthlyFinance(Member member, Integer month, Integer year);

}

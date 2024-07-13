package com.lgonzalez.controller;

import com.lgonzalez.dto.MonthlyDTO;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.service.IMemberService;
import com.lgonzalez.service.IMonthlyFinanceService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/monthly")
public class MonthlyFinanceController {

    @Autowired
    private IMonthlyFinanceService service;
    @Autowired
    private IMemberService memberService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{memberId}/{month}/{year}")
    public ResponseEntity<MonthlyDTO> getMonthlyFinance(@PathVariable Long memberId, @PathVariable Integer month, @PathVariable Integer year) {
        Member member = memberService.findById(memberId);
        MonthlyDTO monthlyDTO =modelMapper.map(service.getMonthlyFinance(member, month, year), MonthlyDTO.class); ;
        return new ResponseEntity<>(monthlyDTO, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody MonthlyDTO m) {
        memberService.findById(m.getIdMember());
        MonthlyFinance monthlyFinance = service.save(modelMapper.map(m, MonthlyFinance.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyFinance.getIdMonthlyFinance()).toUri();
        return ResponseEntity.created(location).build();
    }

}


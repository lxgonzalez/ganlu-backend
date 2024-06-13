package com.lgonzalez.controller;

import com.lgonzalez.dto.DistributionDTO;
import com.lgonzalez.dto.MemberDTO;
import com.lgonzalez.dto.MonthlyDTO;
import com.lgonzalez.dto.MonthlyFinanceDTO;
import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.service.IMemberService;
import com.lgonzalez.service.IMonthlyFinanceService;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<MonthlyDTO>> getMonthlyFinance(@PathVariable UUID memberId, @PathVariable Integer month, @PathVariable Integer year) {

        Member member = memberService.findById(memberId);
        List<MonthlyDTO> list = service.getMonthlyFinance(member, month, year).stream().map(m -> modelMapper.map(m, MonthlyDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Void> save(@Valid @RequestBody MonthlyDTO m) {

        MonthlyFinance monthlyFinance = service.save(modelMapper.map(m, MonthlyFinance.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(monthlyFinance.getIdMonthlyFinance()).toUri();
        return ResponseEntity.created(location).build();
    }

}


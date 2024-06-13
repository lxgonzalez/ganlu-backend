package com.lgonzalez.controller;

import com.lgonzalez.dto.DistributionDTO;
import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.service.IDistributionService;
import com.lgonzalez.service.IMemberService;
import com.lgonzalez.service.IMonthlyFinanceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/distribution")
public class DistributionController {

    @Autowired
    private IDistributionService service;
    @Autowired
    private IMonthlyFinanceService monthlyFinanceService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<Void> createDistribution(@RequestBody DistributionDTO distributionDTO) {

        Distribution distribution =  mapper.map(distributionDTO, Distribution.class);
        Member member = memberService.findById(distributionDTO.getMonthlyDTO().getIdMember());

        distribution.setMonthlyFinance(monthlyFinanceService.getMonthlyFinance(member,distributionDTO.getMonthlyDTO().getMonthFinance(),distributionDTO.getMonthlyDTO().getYearFinance()).get(0));

        Double total = distribution.getMonthlyFinance().getSalary();
        distribution.setTotal(total*((double) distribution.getPercentage() /100));

        service.save(distribution);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(distribution.getIdDistribution()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/create503020/{memberId}/{month}/{year}")
    public ResponseEntity<Void> create503020(@PathVariable UUID memberId, @PathVariable Integer month, @PathVariable Integer year) {
        List<URI> uriList = new ArrayList<>();

        Member member = memberService.findById(memberId);
        MonthlyFinance monthlyFinance = monthlyFinanceService.getMonthlyFinance(member,month,year).get(0);

        service.create503020(member, monthlyFinance).forEach(d -> {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(d.getIdDistribution())
                    .toUri();
            uriList.add(location);
        });

        return ResponseEntity.created(uriList.get(0))
                .header("Resource-URIs", uriList.toString())
                .build();
    }
}

package com.lgonzalez.controller;

import com.lgonzalez.dto.DistributionDTO;
import com.lgonzalez.dto.DistributionDetailDTO;
import com.lgonzalez.model.Distribution;
import com.lgonzalez.model.DistributionDetail;
import com.lgonzalez.model.Member;
import com.lgonzalez.model.MonthlyFinance;
import com.lgonzalez.service.IDistributionDetailService;
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

@RestController
@RequestMapping("/distribution")
public class DistributionController {

    @Autowired
    private IDistributionService distributionService;
    @Autowired
    private IMonthlyFinanceService monthlyFinanceService;

    @Autowired
    private IDistributionDetailService detailService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<Void> createDistribution(@RequestBody DistributionDTO distributionDTO) {

        Distribution distribution =  mapper.map(distributionDTO, Distribution.class);
        Member member = memberService.findById(distributionDTO.getMonthlyFinance().getIdMember());
        MonthlyFinance monthlyFinance = monthlyFinanceService.getMonthlyFinance(member,distributionDTO.getMonthlyFinance().getMonthFinance(),distributionDTO.getMonthlyFinance().getYearFinance());

        distribution.setMonthlyFinance(monthlyFinance);

        Double total = distribution.getMonthlyFinance().getSalary();
        distribution.setTotal(total*((double) distribution.getPercentage() /100));
        distribution.setTotalFinal(total*((double) distribution.getPercentage() /100));
        distributionService.save(distribution);

        monthlyFinance = monthlyFinanceService.getMonthlyFinance(member,distributionDTO.getMonthlyFinance().getMonthFinance(),distributionDTO.getMonthlyFinance().getYearFinance());
        monthlyFinanceService.updateTotalMonthlyFinance(monthlyFinance);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(distribution.getIdDistribution()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/create503020/{memberId}/{month}/{year}")
    public ResponseEntity<Void> create503020(@PathVariable Long memberId, @PathVariable Integer month, @PathVariable Integer year) {
        List<URI> uriList = new ArrayList<>();

        Member member = memberService.findById(memberId);
        MonthlyFinance monthlyFinance = monthlyFinanceService.getMonthlyFinance(member,month,year);

        monthlyFinance = monthlyFinanceService.getMonthlyFinance503020(monthlyFinance);

        distributionService.create503020(member, monthlyFinance).forEach(d -> {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(d.getIdDistribution())
                    .toUri();
            uriList.add(location);
        });

        monthlyFinance = monthlyFinanceService.getMonthlyFinance(member,month,year);
        monthlyFinanceService.updateTotalMonthlyFinance(monthlyFinance);


        return ResponseEntity.created(uriList.get(0))
                .header("Resource-URIs", uriList.toString())
                .build();
    }

    @PostMapping("/detail/{idDistribution}")
    public ResponseEntity<Void> detailDistribution(@RequestBody DistributionDetailDTO distributionDetailDTO, @PathVariable Long idDistribution) {
        DistributionDetail distributionDetail = mapper.map(distributionDetailDTO, DistributionDetail.class);

        Distribution distribution = distributionService.findById(idDistribution);
        distributionDetail.setDistribution(distribution);
        detailService.save(distributionDetail);

        distribution.setTotalFinal(distribution.getTotalFinal() - distributionDetail.getSubtotal());
        distributionService.update(distribution);

        MonthlyFinance monthlyFinance = distribution.getMonthlyFinance();
        monthlyFinanceService.updateTotalMonthlyFinance(monthlyFinance);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(distributionDetail.getIdDistributionDetail()).toUri();
        return ResponseEntity.created(location).build();

    }


}

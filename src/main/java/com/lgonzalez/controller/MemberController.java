package com.lgonzalez.controller;

import com.lgonzalez.dto.MemberDTO;
import com.lgonzalez.model.Member;
import com.lgonzalez.service.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private IMemberService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> findAll(){

        List<MemberDTO> list = service.findAll().stream().map(p -> modelMapper.map(p, MemberDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MemberDTO memberDTO){
        Member p = service.save(modelMapper.map(memberDTO,Member.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdMember()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> findById(@PathVariable UUID id){
        Member p = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(p, MemberDTO.class));
    }

    @PutMapping()
    public ResponseEntity<Void> update(@Valid @RequestBody MemberDTO patientDTO){
        Member p = service.update(modelMapper.map(patientDTO,Member.class));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

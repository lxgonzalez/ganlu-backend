package com.lgonzalez.controller;

import com.lgonzalez.dto.CategoryDTO;
import com.lgonzalez.dto.MemberDTO;
import com.lgonzalez.model.Category;
import com.lgonzalez.model.Member;
import com.lgonzalez.service.ICategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> list = categoryService.findAll().stream().map(category -> mapper.map(category, CategoryDTO.class)).toList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO categoryDTO){
        Category c = categoryService.save(mapper.map(categoryDTO,Category.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdCategory()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO){
        Category c = categoryService.update(mapper.map(categoryDTO,Category.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdCategory()).toUri();

        return ResponseEntity.created(location).build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteByID(@Valid @RequestBody CategoryDTO categoryDTO){
        Category c = mapper.map(categoryDTO,Category.class);
        categoryService.delete(c.getIdCategory());
        return ResponseEntity.noContent().build();
    }

}

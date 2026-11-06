package com.portafolio.controller;

import com.portafolio.entity.ExtraInfo;
import com.portafolio.service.ExtraInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/extra-info")
public class ExtraInfoController {

    private final ExtraInfoService extraInfoService;

    public ExtraInfoController(ExtraInfoService extraInfoService) {
        this.extraInfoService = extraInfoService;
    }

    @GetMapping
    public List<ExtraInfo> findAll() {
        return extraInfoService.findAll();
    }

    @GetMapping("/{id}")
    public ExtraInfo findById(@PathVariable Long id) {
        return extraInfoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<ExtraInfo> create(@RequestBody ExtraInfo extraInfo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(extraInfoService.create(extraInfo));
    }

    @PutMapping("/{id}")
    public ExtraInfo update(@PathVariable Long id, @RequestBody ExtraInfo extraInfo) {
        return extraInfoService.update(id, extraInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        extraInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

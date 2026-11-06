package com.portafolio.controller;

import com.portafolio.entity.Study;
import com.portafolio.service.StudyService;
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
@RequestMapping("/api/studies")
public class StudyController {

    private final StudyService studiesService;

    public StudyController(StudyService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping
    public List<Study> findAll() {
        return studiesService.findAll();
    }

    @GetMapping("/{id}")
    public Study findById(@PathVariable Long id) {
        return studiesService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Study> create(@RequestBody Study study) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studiesService.create(study));
    }

    @PutMapping("/{id}")
    public Study update(@PathVariable Long id, @RequestBody Study study) {
        return studiesService.update(id, study);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studiesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

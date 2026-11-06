package com.portafolio.service.impl;

import com.portafolio.entity.Study;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.StudyRepository;
import com.portafolio.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudiesServiceImpl implements StudyService {

    @Autowired
    private StudyRepository studyRepository;

    public StudiesServiceImpl(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }

    @Override
    public Study findById(Long id) {
        return studyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Estudio", id));
    }

    @Override
    public Study create(Study study) {
        return studyRepository.save(study);
    }

    @Override
    public Study update(Long id, Study study) {
        Study existingStudy = findById(id);
        existingStudy.setTitle(study.getTitle());
        existingStudy.setUniversity(study.getUniversity());
        existingStudy.setStartYear(study.getStartYear());
        existingStudy.setEndYear(study.getEndYear());
        return studyRepository.save(existingStudy);
    }

    @Override
    public void delete(Long id) {
        studyRepository.delete(findById(id));
    }
}

package com.portafolio.service;

import com.portafolio.entity.Study;

import java.util.List;

public interface StudyService {

    List<Study> findAll();

    Study findById(Long id);

    Study create(Study study);

    Study update(Long id, Study study);

    void delete(Long id);
}

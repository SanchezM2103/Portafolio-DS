package com.portafolio.service;

import com.portafolio.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project findById(Long id);

    Project create(Project project);

    Project update(Long id, Project project);

    void delete(Long id);

}

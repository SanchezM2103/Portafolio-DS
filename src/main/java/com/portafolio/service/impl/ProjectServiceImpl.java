package com.portafolio.service.impl;

import com.portafolio.entity.Project;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.ProjectRepository;
import com.portafolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.projectRepository = repository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proyecto", id));
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Long id, Project project) {
        Project existingProject = findById(id);
        existingProject.setTitleProject(project.getTitleProject());
        existingProject.setDesc(project.getDesc());
        existingProject.setUrl(project.getUrl());
        existingProject.setImg(project.getImg());
        existingProject.setSkills(project.getSkills());
        return projectRepository.save(existingProject);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(findById(id));
    }

}

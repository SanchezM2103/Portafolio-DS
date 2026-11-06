package com.portafolio.service.impl;

import com.portafolio.entity.Skill;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.SkillRepository;
import com.portafolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Habilidad", id));
    }

    @Override
    public Skill create(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        Skill existingSkill = findById(id);
        existingSkill.setName(skill.getName());
        existingSkill.setLevel(skill.getLevel());
        return skillRepository.save(existingSkill);
    }

    @Override
    public void delete(Long id) {
        skillRepository.delete(findById(id));
    }
}

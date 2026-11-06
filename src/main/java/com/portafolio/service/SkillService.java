package com.portafolio.service;

import com.portafolio.entity.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill findById(Long id);

    Skill create(Skill skill);

    Skill update(Long id, Skill skill);

    void delete(Long id);

}

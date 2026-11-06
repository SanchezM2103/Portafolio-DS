package com.portafolio.service;

import com.portafolio.entity.Language;

import java.util.List;

public interface LanguageService {

    List<Language> findAll();

    Language findById(Long id);

    Language create(Language language);

    Language update(Long id, Language language);

    void delete(Long id);

}

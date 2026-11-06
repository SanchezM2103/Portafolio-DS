package com.portafolio.service.impl;

import com.portafolio.entity.Language;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.LanguageRepository;
import com.portafolio.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Idioma", id));
    }

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language update(Long id, Language language) {
        Language existingLanguage = findById(id);
        existingLanguage.setLanguage(language.getLanguage());
        existingLanguage.setLanguageLevel(language.getLanguageLevel());
        return languageRepository.save(existingLanguage);
    }

    @Override
    public void delete(Long id) {
        languageRepository.delete(findById(id));
    }
}

package com.portafolio.entity;

import com.portafolio.enums.Level;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "language", nullable = false, length = 100)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_level", nullable = false, length = 20)
    private Level languageLevel;

    public Long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Level getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(Level languageLevel) {
        this.languageLevel = languageLevel;
    }
}

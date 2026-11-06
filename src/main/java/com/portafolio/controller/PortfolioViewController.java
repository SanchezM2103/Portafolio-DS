package com.portafolio.controller;

import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.service.CourseService;
import com.portafolio.service.ExtraInfoService;
import com.portafolio.service.LanguageService;
import com.portafolio.service.ProfileService;
import com.portafolio.service.ProjectService;
import com.portafolio.service.SkillService;
import com.portafolio.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Year;

@Controller
public class PortfolioViewController {

    private final ProfileService profileService;
    private final ProjectService projectService;
    private final SkillService skillService;
    private final StudyService studyService;
    private final CourseService courseService;
    private final LanguageService languageService;
    private final ExtraInfoService extraInfoService;

    public PortfolioViewController(ProfileService profileService,
                                   ProjectService projectService,
                                   SkillService skillService,
                                   StudyService studyService,
                                   CourseService courseService,
                                   LanguageService languageService,
                                   ExtraInfoService extraInfoService) {
        this.profileService = profileService;
        this.projectService = projectService;
        this.skillService = skillService;
        this.studyService = studyService;
        this.courseService = courseService;
        this.languageService = languageService;
        this.extraInfoService = extraInfoService;
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            model.addAttribute("profile", profileService.getProfile());
        } catch (ResourceNotFoundException exception) {
            model.addAttribute("profile", null);
        }

        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("studies", studyService.findAll());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("extraInfo", extraInfoService.findAll());
        model.addAttribute("currentYear", Year.now().getValue());
        return "portfolio";
    }
}

package com.portafolio.controller;

import com.portafolio.entity.Profile;
import com.portafolio.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public Profile getProfile() {
        return profileService.getProfile();
    }

    @PostMapping
    public ResponseEntity<Profile> create(@RequestBody Profile profile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(profile));
    }

    @PutMapping
    public Profile update(@RequestBody Profile profile) {
        return profileService.update(profile);
    }
}

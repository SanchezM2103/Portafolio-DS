package com.portafolio.service.impl;

import com.portafolio.entity.Profile;
import com.portafolio.exception.ProfileAlreadyExistsException;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.ProfileRepository;
import com.portafolio.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Profile getProfile() {
        return profileRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil"));
    }

    @Override
    public Profile create(Profile profile) {
        if (profileRepository.findById(1L).isPresent()) {
            throw new ProfileAlreadyExistsException();
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        Profile existingProfile = getProfile();
        existingProfile.setName(profile.getName());
        existingProfile.setSurname(profile.getSurname());
        existingProfile.setAge(profile.getAge());
        existingProfile.setWorkStatus(profile.getWorkStatus());
        return profileRepository.save(existingProfile);
    }

    @Override
    public void delete(){
        Profile profile = this.getProfile();
        profileRepository.delete(profile);
    }


}

package com.portafolio.service;

import com.portafolio.entity.Profile;

public interface ProfileService {

    Profile getProfile();

    Profile create(Profile profile);

    Profile update(Profile profile);

    void delete();

}

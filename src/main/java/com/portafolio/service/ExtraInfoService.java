package com.portafolio.service;

import com.portafolio.entity.ExtraInfo;

import java.util.List;

public interface ExtraInfoService {

    List<ExtraInfo> findAll();

    ExtraInfo findById(Long id);

    ExtraInfo create(ExtraInfo extraInfo);

    ExtraInfo update(Long id, ExtraInfo extraInfo);

    void delete(Long id);

}

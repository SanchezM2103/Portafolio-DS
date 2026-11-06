package com.portafolio.service.impl;

import com.portafolio.entity.ExtraInfo;
import com.portafolio.exception.ResourceNotFoundException;
import com.portafolio.repository.ExtraInfoRepository;
import com.portafolio.service.ExtraInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraInfoServiceImpl implements ExtraInfoService {

    @Autowired
    private ExtraInfoRepository extraInfoRepository;

    public ExtraInfoServiceImpl(ExtraInfoRepository extraInfoRepository) {
        this.extraInfoRepository = extraInfoRepository;
    }

    @Override
    public List<ExtraInfo> findAll() {
        return extraInfoRepository.findAll();
    }

    @Override
    public ExtraInfo findById(Long id) {
        return extraInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Información extra", id));
    }

    @Override
    public ExtraInfo create(ExtraInfo extraInfo) {
        return extraInfoRepository.save(extraInfo);
    }

    @Override
    public ExtraInfo update(Long id, ExtraInfo extraInfo) {
        ExtraInfo existingExtraInfo = findById(id);
        existingExtraInfo.setTitle(extraInfo.getTitle());
        existingExtraInfo.setDesc(extraInfo.getDesc());
        existingExtraInfo.setYear(extraInfo.getYear());
        return extraInfoRepository.save(existingExtraInfo);
    }

    @Override
    public void delete(Long id) {
        extraInfoRepository.delete(findById(id));
    }
}

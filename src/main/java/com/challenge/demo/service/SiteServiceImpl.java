package com.challenge.demo.service;

import com.challenge.demo.dto.SiteDTO;
import com.challenge.demo.model.Site;
import com.challenge.demo.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public SiteDTO create(SiteDTO siteDto) {
        final Site newSite = SiteDTO.createSite(siteDto);
        return SiteDTO.build(siteRepository.save(newSite));
    }

    @Override
    public List<SiteDTO> getSites() {
        return SiteDTO.build(siteRepository.findAll());
    }

    @Override
    public Optional<SiteDTO> updateSite(SiteDTO siteDto, Long siteId) {
        return siteRepository
                .findById(siteId)
                .map(site -> {
                    site.setUrl(siteDto.getUrl());
                    return SiteDTO.build(siteRepository.save(site));
                });
    }

    @Override
    public Optional<SiteDTO> deleteSite(Long siteId) {
        return siteRepository
                .findById(siteId)
                .map(site -> {
                    siteRepository.delete(site);
                    return SiteDTO.build(site);
                });
    }

    @Override
    public Optional<SiteDTO> getSiteById(Long siteId) {
        return siteRepository
                .findById(siteId)
                .map(site -> SiteDTO.build(site));
    }

}

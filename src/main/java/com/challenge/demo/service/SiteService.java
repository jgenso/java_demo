package com.challenge.demo.service;

import com.challenge.demo.dto.SiteDTO;

import java.util.List;
import java.util.Optional;

public interface SiteService {
    SiteDTO create(SiteDTO siteDto);

    List<SiteDTO> getSites();

    Optional<SiteDTO> updateSite(SiteDTO siteDto, Long siteId);

    Optional<SiteDTO> deleteSite(Long siteId);

    Optional<SiteDTO> getSiteById(Long siteId);

}

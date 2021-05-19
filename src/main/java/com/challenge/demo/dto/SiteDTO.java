package com.challenge.demo.dto;

import com.challenge.demo.model.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SiteDTO {

    private Long siteId;

    private UUID siteUUID;

    private String url;

    private Date createdAt;

    private Date updatedAt;

    public static SiteDTO build(Site site) {
        final SiteDTO obj = new SiteDTO();
        obj.setSiteId(site.getSiteId());
        obj.setSiteUUID(site.getSiteUUID());
        obj.setUrl(site.getUrl());
        obj.setUpdatedAt(site.getUpdatedAt());
        obj.setCreatedAt(site.getCreatedAt());

        return obj;
    }

    public static List<SiteDTO> build(List<Site> sites) {
        final List<SiteDTO> ret = new ArrayList<>();

        for (Site site : sites) {
            ret.add(build(site));
        }

        return ret;
    }

    public static Site createSite(final SiteDTO incomingSite, final Site site) {
        final Site newSite = new Site();
        newSite.setSiteUUID(incomingSite.getSiteUUID());
        newSite.setUrl(incomingSite.getUrl());

        return newSite;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(final Long siteId) {
        this.siteId = siteId;
    }

    public UUID getSiteUUID() {
        return siteUUID;
    }

    public void setSiteUUID(final UUID siteUUID) {
        this.siteUUID = siteUUID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

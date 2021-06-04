package com.challenge.demo.controller;

import com.challenge.demo.dto.SiteDTO;
import com.challenge.demo.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sites")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<SiteDTO> createSite(@RequestBody SiteDTO incomingSite) {
		return ResponseEntity.ok(siteService.create(incomingSite));
	}

	@GetMapping()
	public ResponseEntity<List<SiteDTO>> getSites() {
		return Optional
				.ofNullable(siteService.getSites())
				.map(sites -> ResponseEntity.ok(sites))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<SiteDTO> updateSite(@RequestBody SiteDTO updatedSite, @PathVariable(value = "id") Long siteId) {
		return siteService
				.updateSite(updatedSite, siteId)
				.map(site -> {
					return new ResponseEntity<SiteDTO>(site, HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SiteDTO> deleteSite(@PathVariable(value = "id") Long siteId) {
		return siteService
				.deleteSite(siteId)
				.map(site -> {
					return new ResponseEntity<SiteDTO>(site, HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SiteDTO> getSiteById(@PathVariable(value = "id") Long siteId) {
		return siteService
				.getSiteById(siteId)
				.map(site -> {
					return new ResponseEntity<SiteDTO>(site, HttpStatus.OK);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}


}

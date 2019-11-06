package com.incentives.piggyback.partner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;
import com.incentives.piggyback.partner.service.PartnerService;
import com.incentives.piggyback.partner.util.RestResponse;
import com.incentives.piggyback.partner.util.RestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/partner")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;

	@PostMapping
	public ResponseEntity<RestResponse<PartnerEntity>> createPartner(@RequestBody Partner partner,HttpServletRequest request) {
		if(null!=request && null!=request.getHeader("Authorization")) {
			return RestUtils.successResponse(partnerService.createPartner(partner, request));
		}else {
			log.info("User not authorized to create partners");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@DeleteMapping
	public ResponseEntity<RestResponse<String>> deletePartner(
			@RequestParam("partnerId") String partnerId) {
		return RestUtils.successResponse(partnerService.deletePartner(partnerId));
	}
	
	@PutMapping
	public ResponseEntity<RestResponse<PartnerEntity>> updatePartner(
			@RequestBody Partner partner, HttpServletRequest request) {
		if(null!=request && null!=request.getHeader("Authorization")) {
			return RestUtils.successResponse(partnerService.updatePartner(partner, request));
		}else {
			log.info("User not authorized to create partners");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<RestResponse<PartnerEntity>> getPartner(
			@RequestParam("partnerId") String partnerId) {
		return RestUtils.successResponse(partnerService.getPartner(partnerId));
	}

	@GetMapping("/")
	public ResponseEntity<List<PartnerEntity>> getAllPartner() {
		return ResponseEntity.ok(partnerService.getAllPartner());
	}
}
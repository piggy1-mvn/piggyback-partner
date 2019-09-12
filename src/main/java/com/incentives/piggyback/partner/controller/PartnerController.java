package com.incentives.piggyback.partner.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.publisher.PartnerEventPublisher;
import com.incentives.piggyback.partner.service.PartnerService;
import com.incentives.piggyback.partner.util.CommonUtility;
import com.incentives.piggyback.partner.util.RestResponse;
import com.incentives.piggyback.partner.util.RestUtils;
import com.incentives.piggyback.partner.util.constants.Constant;

@RestController
@RequestMapping("/partner")
public class PartnerController {

	@Autowired
	private PartnerEventPublisher.PubsubOutboundGateway messagingGateway;

	@Autowired
	private PartnerService partnerService;

	@PostMapping
	public ResponseEntity<RestResponse<PartnerEntity>> createPartner(@RequestBody PartnerEntity partner) {
		return RestUtils.successResponse(partnerService.createPartner(partner));
	}

	@DeleteMapping
	public ResponseEntity<RestResponse<String>> deletePartner(
			@RequestParam("partnerId") String partnerId) {
		return RestUtils.successResponse(partnerService.deletePartner(partnerId));
	}
	
	@PutMapping
	public ResponseEntity<RestResponse<PartnerEntity>> updatePartner(
			@RequestBody PartnerEntity partner) {
		return RestUtils.successResponse(partnerService.updatePartner(partner));
	}
	
	@GetMapping
	public ResponseEntity<RestResponse<PartnerEntity>> getPartner(
			@RequestParam("partnerId") String partnerId) {
		return RestUtils.successResponse(partnerService.getPartner(partnerId));
	}



	@PostMapping("/publish")
	public void publishMessage(@RequestParam("message") String message) {
		//PUSHING MESSAGES TO GCP
		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						message,
						Constant.PARTNER_CREATED_EVENT,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.PARTNER_SOURCE_ID
						));
	}
}
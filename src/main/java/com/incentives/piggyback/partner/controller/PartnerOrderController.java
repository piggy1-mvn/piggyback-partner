package com.incentives.piggyback.partner.controller;

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

import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.PartnerOrder;
import com.incentives.piggyback.partner.service.PartnerOrderService;
import com.incentives.piggyback.partner.util.RestResponse;
import com.incentives.piggyback.partner.util.RestUtils;

@RestController
@RequestMapping("/order")
public class PartnerOrderController {

	@Autowired
	private PartnerOrderService partnerOrderService;

	@PostMapping
	public ResponseEntity<RestResponse<PartnerOrderEntity>> createPartnerOrder(@RequestBody PartnerOrder partnerOrder) {
		return RestUtils.successResponse(partnerOrderService.createPartnerOrder(partnerOrder));
	}

	@DeleteMapping
	public ResponseEntity<RestResponse<String>> deletePartnerOrder(
			@RequestParam("orderId") String orderId) {
		return RestUtils.successResponse(partnerOrderService.deletePartnerOrder(orderId));
	}

	@PutMapping
	public ResponseEntity<RestResponse<PartnerOrderEntity>> updatePartnerOrder(
			@RequestBody PartnerOrder partnerOrder) {
		return RestUtils.successResponse(partnerOrderService.updatePartnerOrder(partnerOrder));
	}

	@GetMapping
	public ResponseEntity<RestResponse<PartnerOrderEntity>> getPartnerOrder(
			@RequestParam("orderId") String orderId) {
		return RestUtils.successResponse(partnerOrderService.getPartnerOrder(orderId));
	}

	@GetMapping("/categories")
	public ResponseEntity<ResponseEntity<String>> getPartnerOrderType() {
		return ResponseEntity.ok(partnerOrderService.getPartnerOrderType());
	}

	@GetMapping("/")
	public ResponseEntity<RestResponse<Iterable<PartnerOrderEntity>>> getAllPartnerOrder() {
		return RestUtils.successResponse(partnerOrderService.getAllPartnerOrder());
	}


}
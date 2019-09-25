package com.incentives.piggyback.partner.entity;

import lombok.Data;

@Data
public class PartnerOrder {

	private String orderId;
	private String partnerId;
	private String orderType;
	private String orderStatus;
	private Integer optimizationDuration;
	private Location orderLocation;
	private Integer maxOptimizations;
	private Integer optimizationRadius;
	private String initiatorUserId;
	private String partnerDisplayName;
	private String partnerRedirectUrl;
	private String partnerWebHookAddress;
}
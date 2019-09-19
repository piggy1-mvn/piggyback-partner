package com.incentives.piggyback.partner.entity;

import lombok.Data;

@Data
public class PartnerOrder {

	private String orderId;
	private String partnerId;
	private String orderType;
	private String orderStatus;
	private double optimizationDuration;
	private Location orderLocation;
	private int maxOptimizations;
	private double optimizationRadius;
	private String initiatorUserId;
	private String partnerDisplayName;
	private String partnerRedirectUrl;
}
package com.incentives.piggyback.partner.entity;

import java.util.List;

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
	private List<String> interestCategories;
	private String vendorDisplayName;
	private String vendorRedirectUrl;
}
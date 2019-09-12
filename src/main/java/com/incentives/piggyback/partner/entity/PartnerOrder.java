package com.incentives.piggyback.partner.entity;

import java.util.List;

import lombok.Data;

@Data
public class PartnerOrder {

	private String orderId;
	private String userName;
	private String orderType;
	private double deliveryDuration;
	private int MaxAllowedOrders;
	private Location orderLocation;
	private double timestamp;
	private String initiatorUserId;
	private String orderStatus;
	private List<String> userIds;
}
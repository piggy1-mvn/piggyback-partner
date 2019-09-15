package com.incentives.piggyback.partner.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.incentives.piggyback.partner.entity.Location;

import lombok.Data;

@Data
@Document(collection = "partnerOrder")
public class PartnerOrderEntity {

	@Id
	private String orderId;
	private String userName;
	private String orderType;
	private double deliveryDuration;
	private int MaxAllowedOrders;
	private Location orderLocation;
	private String initiatorUserId;
	private String orderStatus;
	private double optimizationRadius;
	private List<String> userIds;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer isActive;
}
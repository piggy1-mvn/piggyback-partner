package com.incentives.piggyback.partner.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.incentives.piggyback.partner.entity.Location;

import lombok.Data;

@Data
@Document(collection = "partnerOrder")
public class PartnerOrderEntity {

	@Id
	private String orderId;
	private String partnerId;
	private String orderType;
	private String orderStatus;
	private Integer optimizationDuration;
	private Location orderLocation;
	private Integer maxOptimizations;
	private Integer optimizationRadius;
	private Long initiatorUserId;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer isActive;
	private String partnerDisplayName;
	private String partnerRedirectUrl;
	private String partnerWebHookAddress;
}
package com.incentives.piggyback.partner.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "partner")
public class PartnerEntity {

	@Id
	private String partnerId;
	private String partnerName;
	private String partnerDescription;
	private String partnerWebHookAddress;
	private String partnerOfficeAddress;
	private String partnerMobile;
	private List<String> userIds;
	private Date createdDate;
	private Date lastModifiedDate;
	private Integer isActive;
}
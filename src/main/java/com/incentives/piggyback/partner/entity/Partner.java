package com.incentives.piggyback.partner.entity;

import lombok.Data;

import java.util.List;

@Data
public class Partner {

	private String partnerId;
	private String partnerName;
	private String partnerDescription;
	private String partnerWebHookAddress;
	private String partnerOfficeAddress;
	private String partnerMobile;
	private List<String> userIds;
}

package com.incentives.piggyback.partner.adapter;

import java.util.Calendar;
import java.util.UUID;

import com.incentives.piggyback.partner.dto.PartnerEntity;

public class ObjectAdapter {

	public static PartnerEntity getPartnerEntity(PartnerEntity partner) {
		partner.setPartnerId(UUID.randomUUID().toString());
		partner.setCreatedDate(Calendar.getInstance().getTime());
		partner.setLastModifiedDate(Calendar.getInstance().getTime());
		partner.setIsActive(1);
		return partner;
	}
	
	public static PartnerEntity updatePartnerEntity(
			PartnerEntity currentPartner, PartnerEntity modifiedPartner) {
		currentPartner.setPartnerDescription(modifiedPartner.getPartnerDescription());
		currentPartner.setPartnerName(modifiedPartner.getPartnerName());
		currentPartner.setPartnerMobile(modifiedPartner.getPartnerMobile());
		currentPartner.setPartnerOfficeAddress(modifiedPartner.getPartnerOfficeAddress());
		currentPartner.setPartnerWebHookAddress(modifiedPartner.getPartnerWebHookAddress());
		currentPartner.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentPartner;
	}
}
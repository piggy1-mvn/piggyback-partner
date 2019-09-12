package com.incentives.piggyback.partner.adapter;

import java.util.Calendar;
import java.util.UUID;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;

public class ObjectAdapter {

	public static PartnerEntity getPartnerEntity(Partner partnerData) {
		PartnerEntity partner = new PartnerEntity();
		partner.setPartnerId(UUID.randomUUID().toString());
		partner.setPartnerDescription(partnerData.getPartnerDescription());
		partner.setPartnerName(partnerData.getPartnerName());
		partner.setPartnerMobile(partnerData.getPartnerMobile());
		partner.setPartnerOfficeAddress(partnerData.getPartnerOfficeAddress());
		partner.setPartnerWebHookAddress(partnerData.getPartnerWebHookAddress());
		partner.setCreatedDate(Calendar.getInstance().getTime());
		partner.setLastModifiedDate(Calendar.getInstance().getTime());
		partner.setIsActive(1);
		return partner;
	}
	
	public static PartnerEntity updatePartnerEntity(
			PartnerEntity currentPartner, Partner modifiedPartner) {
		currentPartner.setPartnerDescription(modifiedPartner.getPartnerDescription());
		currentPartner.setPartnerName(modifiedPartner.getPartnerName());
		currentPartner.setPartnerMobile(modifiedPartner.getPartnerMobile());
		currentPartner.setPartnerOfficeAddress(modifiedPartner.getPartnerOfficeAddress());
		currentPartner.setPartnerWebHookAddress(modifiedPartner.getPartnerWebHookAddress());
		currentPartner.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentPartner;
	}
}
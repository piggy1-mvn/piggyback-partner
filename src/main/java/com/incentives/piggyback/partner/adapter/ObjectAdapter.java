package com.incentives.piggyback.partner.adapter;

import java.util.Calendar;
import java.util.UUID;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;
import com.incentives.piggyback.partner.util.CommonUtility;

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
		partner.setUserIds(partnerData.getUserIds());
		partner.setIsActive(1);
		return partner;
	}

	public static PartnerEntity updatePartnerEntity(
			PartnerEntity currentPartner, Partner modifiedPartner) {
		if (CommonUtility.isValidString(modifiedPartner.getPartnerDescription()))
			currentPartner.setPartnerDescription(modifiedPartner.getPartnerDescription());

		if (CommonUtility.isValidString(modifiedPartner.getPartnerName()))
			currentPartner.setPartnerName(modifiedPartner.getPartnerName());

		if (CommonUtility.isValidString(modifiedPartner.getPartnerMobile()))
			currentPartner.setPartnerMobile(modifiedPartner.getPartnerMobile());

		if (CommonUtility.isValidString(modifiedPartner.getPartnerOfficeAddress()))
			currentPartner.setPartnerOfficeAddress(modifiedPartner.getPartnerOfficeAddress());

		if (CommonUtility.isValidString(modifiedPartner.getPartnerWebHookAddress()))
			currentPartner.setPartnerWebHookAddress(modifiedPartner.getPartnerWebHookAddress());

		currentPartner.setUserIds(modifiedPartner.getUserIds());

		currentPartner.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentPartner;
	}

}
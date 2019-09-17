package com.incentives.piggyback.partner.adapter;

import java.util.Calendar;
import java.util.UUID;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.Partner;
import com.incentives.piggyback.partner.entity.PartnerOrder;
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

	public static PartnerOrderEntity getPartnerOrderEntity(PartnerOrder partnerOrder) {
		PartnerOrderEntity orderEntity = new PartnerOrderEntity();
		orderEntity.setOrderId(partnerOrder.getOrderId());
		orderEntity.setPartnerId(partnerOrder.getPartnerId());
		orderEntity.setOrderType(partnerOrder.getOrderType());
		orderEntity.setOptimizationDuration(partnerOrder.getOptimizationDuration());
		orderEntity.setMaxOptimizations(partnerOrder.getMaxOptimizations());
		orderEntity.setOrderLocation(partnerOrder.getOrderLocation());
		orderEntity.setInitiatorUserId(partnerOrder.getInitiatorUserId());
		orderEntity.setOrderStatus(partnerOrder.getOrderStatus());
		orderEntity.setOptimizationRadius(partnerOrder.getOptimizationRadius());
		orderEntity.setCreatedDate(Calendar.getInstance().getTime());
		orderEntity.setLastModifiedDate(Calendar.getInstance().getTime());
		orderEntity.setIsActive(1);
		return orderEntity;
	}
	
	public static PartnerOrderEntity updatePartnerOrderEntity(
			PartnerOrderEntity currentOrder, PartnerOrder modifiedOrder) {
		if (CommonUtility.isValidString(modifiedOrder.getPartnerId()))
			currentOrder.setPartnerId(modifiedOrder.getPartnerId());

		if (CommonUtility.isValidString(modifiedOrder.getOrderType()))
			currentOrder.setOrderType(modifiedOrder.getOrderType());

		if (modifiedOrder.getOptimizationDuration() != 0)
			currentOrder.setOptimizationDuration(modifiedOrder.getOptimizationDuration());

		if (modifiedOrder.getMaxOptimizations() != 0)
			currentOrder.setMaxOptimizations(modifiedOrder.getMaxOptimizations());

		if (!CommonUtility.isNullObject(modifiedOrder.getOrderLocation()))
			currentOrder.setOrderLocation(modifiedOrder.getOrderLocation());

		if (CommonUtility.isValidString(modifiedOrder.getInitiatorUserId()))
			currentOrder.setInitiatorUserId(modifiedOrder.getInitiatorUserId());

		if (CommonUtility.isValidString(modifiedOrder.getOrderStatus()))
			currentOrder.setOrderStatus(modifiedOrder.getOrderStatus());

		if (modifiedOrder.getOptimizationRadius() != 0)
			currentOrder.setOptimizationRadius(modifiedOrder.getOptimizationRadius());

		currentOrder.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentOrder;
	}
}
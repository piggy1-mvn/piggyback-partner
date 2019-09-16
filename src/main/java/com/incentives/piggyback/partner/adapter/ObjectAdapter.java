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

		currentPartner.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentPartner;
	}

	public static PartnerOrderEntity getPartnerOrderEntity(PartnerOrder partnerOrder) {
		PartnerOrderEntity orderEntity = new PartnerOrderEntity();
		orderEntity.setOrderId(partnerOrder.getOrderId());
		orderEntity.setUserName(partnerOrder.getUserName());
		orderEntity.setOrderType(partnerOrder.getOrderType());
		orderEntity.setDeliveryDuration(partnerOrder.getDeliveryDuration());
		orderEntity.setMaxAllowedOrders(partnerOrder.getMaxAllowedOrders());
		orderEntity.setOrderLocation(partnerOrder.getOrderLocation());
		orderEntity.setInitiatorUserId(partnerOrder.getInitiatorUserId());
		orderEntity.setOrderStatus(partnerOrder.getOrderStatus());
		orderEntity.setOptimizationRadius(partnerOrder.getOptimizationRadius());
		orderEntity.setUserIds(partnerOrder.getUserIds());
		orderEntity.setCreatedDate(Calendar.getInstance().getTime());
		orderEntity.setLastModifiedDate(Calendar.getInstance().getTime());
		orderEntity.setIsActive(1);
		return orderEntity;
	}
	
	public static PartnerOrderEntity updatePartnerOrderEntity(
			PartnerOrderEntity currentOrder, PartnerOrder modifiedOrder) {
		if (CommonUtility.isValidString(modifiedOrder.getUserName()))
			currentOrder.setUserName(modifiedOrder.getUserName());

		if (CommonUtility.isValidString(modifiedOrder.getOrderType()))
			currentOrder.setOrderType(modifiedOrder.getOrderType());

		if (modifiedOrder.getDeliveryDuration() != 0)
			currentOrder.setDeliveryDuration(modifiedOrder.getDeliveryDuration());

		if (modifiedOrder.getMaxAllowedOrders() != 0)
			currentOrder.setMaxAllowedOrders(modifiedOrder.getMaxAllowedOrders());

		if (!CommonUtility.isNullObject(modifiedOrder.getOrderLocation()))
			currentOrder.setOrderLocation(modifiedOrder.getOrderLocation());

		if (CommonUtility.isValidString(modifiedOrder.getInitiatorUserId()))
			currentOrder.setInitiatorUserId(modifiedOrder.getInitiatorUserId());

		if (CommonUtility.isValidString(modifiedOrder.getOrderStatus()))
			currentOrder.setOrderStatus(modifiedOrder.getOrderStatus());

		if (modifiedOrder.getOptimizationRadius() != 0)
			currentOrder.setOptimizationRadius(modifiedOrder.getOptimizationRadius());

		if (CommonUtility.isValidList(modifiedOrder.getUserIds()))
			currentOrder.setUserIds(modifiedOrder.getUserIds());

		currentOrder.setLastModifiedDate(Calendar.getInstance().getTime());
		return currentOrder;
	}
}
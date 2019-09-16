package com.incentives.piggyback.partner.service;

import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.PartnerOrder;

public interface PartnerOrderService {

	PartnerOrderEntity createPartnerOrder(PartnerOrder partnerOrder);

	String deletePartnerOrder(String orderId);

	PartnerOrderEntity updatePartnerOrder(PartnerOrder partnerOrder);

	PartnerOrderEntity getPartnerOrder(String orderId);

	Iterable<PartnerOrderEntity> getAllPartnerOrder();
}
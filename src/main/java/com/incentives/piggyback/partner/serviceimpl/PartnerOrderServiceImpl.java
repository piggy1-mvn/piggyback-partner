package com.incentives.piggyback.partner.serviceimpl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.incentives.piggyback.partner.adapter.ObjectAdapter;
import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.PartnerOrder;
import com.incentives.piggyback.partner.exception.ExceptionResponseCode;
import com.incentives.piggyback.partner.exception.PiggyException;
import com.incentives.piggyback.partner.publisher.PartnerEventPublisher;
import com.incentives.piggyback.partner.repository.PartnerOrderRepository;
import com.incentives.piggyback.partner.service.PartnerOrderService;
import com.incentives.piggyback.partner.service.PartnerService;
import com.incentives.piggyback.partner.util.CommonUtility;
import com.incentives.piggyback.partner.util.constants.Constant;
import com.incentives.piggyback.partner.util.constants.Preferences;

@Service
public class PartnerOrderServiceImpl implements PartnerOrderService {

	@Autowired
	private PartnerOrderRepository partnerOrderRepository;
	
	@Autowired
	private PartnerService partnerService;

	@Autowired
	private PartnerEventPublisher.PubsubOutboundGateway messagingGateway;

	Gson gson = new Gson();

	@Override
	public PartnerOrderEntity createPartnerOrder(PartnerOrder partnerOrder) {
		PartnerEntity partner = partnerService.getPartner(partnerOrder.getPartnerId());
		partnerOrder.setPartnerDisplayName(partner.getPartnerName());
		partnerOrder.setPartnerWebHookAddress(partner.getPartnerWebHookAddress());
		PartnerOrderEntity partnerOrderEntity = partnerOrderRepository.save(ObjectAdapter.getPartnerOrderEntity(partnerOrder));
		publishPartnerOrder(partnerOrderEntity, Constant.PARTNER_ORDER_CREATED_EVENT);
		return partnerOrderEntity;
	}

	@Override
	public PartnerOrderEntity getPartnerOrder(String orderId) {
		Optional<PartnerOrderEntity> partnerOrderOptional = partnerOrderRepository.findById(orderId);
		if (partnerOrderOptional.isPresent()) {
			return partnerOrderOptional.get();
		} else {
			throw new PiggyException(ExceptionResponseCode.USER_DATA_NOT_FOUND_IN_RESPONSE);
		}
	}

	public Iterable<PartnerOrderEntity> getAllPartnerOrder() {
		return partnerOrderRepository.findAll();
	}

	@Override
	public ResponseEntity getPartnerOrderType() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("orderType", Preferences.values());
		return  ResponseEntity.ok(map);
	}

	@Override
	public String deletePartnerOrder(String orderId) {
		PartnerOrderEntity partnerOrder = getPartnerOrder(orderId);
		partnerOrder.setIsActive(0);
		partnerOrderRepository.save(partnerOrder);
		return "Partner order cancelled!";
	}

	@Override
	public PartnerOrderEntity updatePartnerOrder(PartnerOrder partnerOrder) {
		PartnerOrderEntity currentPartnerOrderValue = getPartnerOrder(partnerOrder.getOrderId());
		PartnerOrderEntity updatedPartnerOrderValue = partnerOrderRepository.save(ObjectAdapter.updatePartnerOrderEntity(currentPartnerOrderValue, partnerOrder));
		publishPartnerOrder(updatedPartnerOrderValue, Constant.PARTNER_ORDER_UPDATED_EVENT);
		return updatedPartnerOrderValue;
	}

	private void publishPartnerOrder(PartnerOrderEntity partnerOrder, String status) {
		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						gson.toJson(partnerOrder),
						status,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.PARTNER_SOURCE_ID
				));

	}
}
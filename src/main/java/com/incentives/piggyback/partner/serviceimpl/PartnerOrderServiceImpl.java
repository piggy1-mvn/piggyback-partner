package com.incentives.piggyback.partner.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incentives.piggyback.partner.adapter.ObjectAdapter;
import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.PartnerOrder;
import com.incentives.piggyback.partner.exception.ExceptionResponseCode;
import com.incentives.piggyback.partner.exception.PiggyException;
import com.incentives.piggyback.partner.repository.PartnerOrderRepository;
import com.incentives.piggyback.partner.service.PartnerOrderService;

@Service
public class PartnerOrderServiceImpl implements PartnerOrderService {

	@Autowired
	private PartnerOrderRepository partnerOrderRepository;

	@Override
	public PartnerOrderEntity createPartnerOrder(PartnerOrder partnerOrder) {
		return partnerOrderRepository.save(ObjectAdapter.getPartnerOrderEntity(partnerOrder));
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
		return partnerOrderRepository.save(ObjectAdapter.updatePartnerOrderEntity(currentPartnerOrderValue, partnerOrder));
	}

}
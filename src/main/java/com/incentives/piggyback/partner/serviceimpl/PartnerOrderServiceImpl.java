package com.incentives.piggyback.partner.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incentives.piggyback.partner.adapter.ObjectAdapter;
import com.incentives.piggyback.partner.dto.PartnerOrderEntity;
import com.incentives.piggyback.partner.entity.PartnerOrder;
import com.incentives.piggyback.partner.exception.ExceptionResponseCode;
import com.incentives.piggyback.partner.exception.PiggyException;
import com.incentives.piggyback.partner.repository.PartnerOrderRepository;
import com.incentives.piggyback.partner.service.PartnerOrderService;

import static java.lang.reflect.Modifier.TRANSIENT;

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

	public Iterable<PartnerOrderEntity> getAllPartnerOrder() {
		return partnerOrderRepository.findAll();
	}

	@Override
	public ResponseEntity getPartnerOrderType() {
		HashMap map = new HashMap();
		DistinctIterable<String> iterable = partnerOrderRepository.getorderType();
		MongoCursor<String> cursor = iterable.iterator();
		List<String> list = new ArrayList<>();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		map.put("orderType", list);
		final Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.excludeFieldsWithModifiers(TRANSIENT)
				.create();
		return  ResponseEntity.ok(gson.toJson(map));
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
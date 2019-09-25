package com.incentives.piggyback.partner.serviceimpl;

import java.util.List;
import java.util.Calendar;
import java.util.Optional;

import com.google.gson.Gson;
import com.incentives.piggyback.partner.publisher.PartnerEventPublisher;
import com.incentives.piggyback.partner.util.CommonUtility;
import com.incentives.piggyback.partner.util.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incentives.piggyback.partner.adapter.ObjectAdapter;
import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;
import com.incentives.piggyback.partner.exception.ExceptionResponseCode;
import com.incentives.piggyback.partner.exception.PiggyException;
import com.incentives.piggyback.partner.repository.PartnerRepository;
import com.incentives.piggyback.partner.service.PartnerService;

@Service
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private PartnerEventPublisher.PubsubOutboundGateway messagingGateway;

	Gson gson = new Gson();

	@Override
	public PartnerEntity createPartner(Partner partner) {
		PartnerEntity partnerEntity = partnerRepository.save(ObjectAdapter.getPartnerEntity(partner));
		publishPartner(partnerEntity, Constant.PARTNER_CREATED_EVENT);
		return partnerEntity;
	}

	@Override
	public PartnerEntity getPartner(String partnerId) {
		Optional<PartnerEntity> partnerOptional = partnerRepository.findById(partnerId);
		if (partnerOptional.isPresent()) {
			return partnerOptional.get();
		} else {
			throw new PiggyException(ExceptionResponseCode.USER_DATA_NOT_FOUND_IN_RESPONSE);
		}
	}

	public List<PartnerEntity> getAllPartner() {
		return partnerRepository.findAll();
	}

	@Override
	public String deletePartner(String partnerId) {
		PartnerEntity partner = getPartner(partnerId);
		partner.setIsActive(0);
		partnerRepository.save(partner);
		return "Partner deactivated successfully!";
	}

	@Override
	public PartnerEntity updatePartner(Partner partner) {
		PartnerEntity currentPartnerValue = getPartner(partner.getPartnerId());
		PartnerEntity partnerEntity = partnerRepository.save(ObjectAdapter.updatePartnerEntity(currentPartnerValue, partner));
		publishPartner(partnerEntity, Constant.PARTNER_UPDATED_EVENT);
		return partnerEntity;
	}

	private void publishPartner(PartnerEntity partner, String status) {
		messagingGateway.sendToPubsub(
				CommonUtility.stringifyEventForPublish(
						gson.toJson(partner),
						status,
						Calendar.getInstance().getTime().toString(),
						"",
						Constant.PARTNER_SOURCE_ID
				));
	}
}
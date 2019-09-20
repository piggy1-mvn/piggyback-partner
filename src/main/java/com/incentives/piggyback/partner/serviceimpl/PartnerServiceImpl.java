package com.incentives.piggyback.partner.serviceimpl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public PartnerEntity createPartner(Partner partner) {
		return partnerRepository.save(ObjectAdapter.getPartnerEntity(partner));
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
		return partnerRepository.save(ObjectAdapter.updatePartnerEntity(currentPartnerValue, partner));
	}

}
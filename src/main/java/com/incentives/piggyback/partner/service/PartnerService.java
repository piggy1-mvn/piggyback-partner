package com.incentives.piggyback.partner.service;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;

public interface PartnerService {

	PartnerEntity createPartner(Partner partner);

	String deletePartner(String partnerId);

	PartnerEntity updatePartner(Partner partner);

	PartnerEntity getPartner(String partnerId);

	Iterable<PartnerEntity> getAllPartner();
}
package com.incentives.piggyback.partner.service;

import com.incentives.piggyback.partner.dto.PartnerEntity;

public interface PartnerService {

	PartnerEntity createPartner(PartnerEntity partner);

	String deletePartner(String partnerId);

	PartnerEntity updatePartner(PartnerEntity partner);

	PartnerEntity getPartner(String partnerId);

}
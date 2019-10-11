package com.incentives.piggyback.partner.service;

import com.incentives.piggyback.partner.dto.PartnerEntity;
import com.incentives.piggyback.partner.entity.Partner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PartnerService {

	PartnerEntity createPartner(Partner partner,HttpServletRequest request);

	String deletePartner(String partnerId);

	PartnerEntity updatePartner(Partner partner, HttpServletRequest request);

	PartnerEntity getPartner(String partnerId);

	List<PartnerEntity> getAllPartner();
}
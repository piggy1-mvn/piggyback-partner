package com.incentives.piggyback.partner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.partner.dto.PartnerOrderEntity;

public interface PartnerOrderRepository extends MongoRepository<PartnerOrderEntity, String> {
}
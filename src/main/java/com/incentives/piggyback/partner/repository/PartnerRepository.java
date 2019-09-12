package com.incentives.piggyback.partner.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.incentives.piggyback.partner.dto.PartnerEntity;

public interface PartnerRepository extends MongoRepository<PartnerEntity, String> {

}
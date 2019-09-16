package com.incentives.piggyback.partner.repository;


import com.mongodb.client.DistinctIterable;

public interface PartnerOrderRepositoryCustom {
    DistinctIterable<String> getorderType();
}

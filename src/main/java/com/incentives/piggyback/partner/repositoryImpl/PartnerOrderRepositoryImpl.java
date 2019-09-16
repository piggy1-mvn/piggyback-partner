package com.incentives.piggyback.partner.repositoryImpl;

import com.incentives.piggyback.partner.repository.PartnerOrderRepositoryCustom;
import com.mongodb.client.DistinctIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(value = "partnerOrderRepositoryImpl")
@Transactional(readOnly = true)
public class PartnerOrderRepositoryImpl implements PartnerOrderRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PartnerOrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public DistinctIterable<String> getOrderType() {
        return mongoTemplate.getCollection("partnerOrder").distinct("orderType",String.class);
    }
}

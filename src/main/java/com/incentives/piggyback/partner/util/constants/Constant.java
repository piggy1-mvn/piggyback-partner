package com.incentives.piggyback.partner.util.constants;

public interface Constant {
    String PARTNER_PUBLISHER_TOPIC = "partner.events.topic";
    String KAFKA_BOOTSTRAP_ADDRESS = "kafka.bootstrap.address";
    String PARTNER_SOURCE_ID = "6";
    String PARTNER_CREATED_EVENT = "Partner Events Created";
    String PARTNER_UPDATED_EVENT = "Partner Events Updated";
    String USER_PARTNER_MAPPING = "User Partner Mapping Event";
    String PARTNER_DEACTIVATED_EVENT = "Partner Events Deactivated";

    Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
}
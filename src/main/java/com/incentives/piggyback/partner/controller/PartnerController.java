package com.incentives.piggyback.partner.controller;

import com.incentives.piggyback.partner.publisher.PartnerEventPublisher;
import com.incentives.piggyback.partner.util.CommonUtility;
import com.incentives.piggyback.partner.util.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class PartnerController {
    @Autowired
    private PartnerEventPublisher.PubsubOutboundGateway messagingGateway;

    @PostMapping("/partners")
    public void publishMessage(@RequestParam("message") String message) {
        //PUSHING MESSAGES TO GCP
        messagingGateway.sendToPubsub(
                CommonUtility.stringifyEventForPublish(
                        message,
                        Constant.PARTNER_CREATED_EVENT,
                        Calendar.getInstance().getTime().toString(),
                        "",
                        Constant.PARTNER_SOURCE_ID
                ));
    }
}

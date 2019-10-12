package com.incentives.piggyback.partner.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserData {

    private Long id;
    private String first_name;
    private String last_name;
    private String user_password;
    private String mobile_number;
    private Boolean mobile_verified;
    private String email;
    private ArrayList<String> user_interests;
    private String user_role;
    private String user_type;
    private String device_id;
    private String user_rsa;
    private String user_partner_id;
}
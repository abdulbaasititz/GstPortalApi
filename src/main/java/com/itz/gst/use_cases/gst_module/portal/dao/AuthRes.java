package com.itz.gst.use_cases.gst_module.portal.dao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthRes {
    private String clientId;
    private String userName;
    private String authToken;
    private String sek;
    private String tokenExpiry;
}

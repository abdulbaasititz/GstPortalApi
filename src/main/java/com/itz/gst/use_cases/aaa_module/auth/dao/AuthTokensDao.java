package com.itz.gst.use_cases.aaa_module.auth.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthTokensDao {
    private String accessToken;
    private String refreshToken;
    private Boolean status;
}

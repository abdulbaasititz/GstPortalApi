package com.itz.gst.use_cases.aaa_module.auth.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDao {
    private String userId;
    private String password;
    private String otp;
}
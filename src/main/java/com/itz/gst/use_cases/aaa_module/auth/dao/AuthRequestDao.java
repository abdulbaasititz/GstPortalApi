package com.itz.gst.use_cases.aaa_module.auth.dao;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthRequestDao {
    @NotNull @NotBlank
    private String userId;
    @NotNull @NotBlank
    private String password;
    @NotNull @NotBlank
    private String gst;
}

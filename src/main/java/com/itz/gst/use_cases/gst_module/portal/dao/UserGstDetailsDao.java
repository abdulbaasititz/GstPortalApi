package com.itz.gst.use_cases.gst_module.portal.dao;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import java.util.Date;

@Getter @Setter
public class UserGstDetailsDao {
    private String userId;
    private String userPassword;

    private Integer userMasterId;
    @NotNull
    private String gstin;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String email;
}

package com.itz.gst.use_cases.aaa_module.user_master.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMasterDao {
    //unique key or email id
    private String userId;

    private String userName;
    private String password;

    private String designation;
    private String phoneNumber;

    private Integer roleMasterId;
    private String email;

    private Boolean isActive;
}

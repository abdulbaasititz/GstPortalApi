package com.itz.gst.use_cases.aaa_module.user_master.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMasterIdDao {
    private Integer id;
    private String designation;
    private String password;
    private String userId;
    private String phoneNumber;
    private String userName;
    private Boolean isActive;
    private Integer roleMasterId;
    private String email;
}

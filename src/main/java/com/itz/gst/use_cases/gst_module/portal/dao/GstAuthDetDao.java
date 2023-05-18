package com.itz.gst.use_cases.gst_module.portal.dao;

public interface GstAuthDetDao {
    String getUserId();
    String getEmail();
    String getClientId();
    String getClientSecret();
    String getIpAddress();
    String getUrl();
    String getUserName();
    String getPassword();
    String getGstin();
    Integer getDiff();
    String getTkn();
}

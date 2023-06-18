package com.itz.gst.use_cases.gst_module.portal.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GenerateEWayDao {
    @JsonProperty("Irn")
    private String irn;
    @JsonProperty("Distance")
    private int distance;
    @JsonProperty("TransMode")
    private String transMode;
    @JsonProperty("TransId")
    private String transId;
    @JsonProperty("TransName")
    private String transName;
    @JsonProperty("TransDocDt")
    private String transDocDt;
    @JsonProperty("TransDocNo")
    private String transDocNo;
    @JsonProperty("VehNo")
    private String vehNo;
    @JsonProperty("VehType")
    private String vehType;


}

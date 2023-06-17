package com.itz.gst.use_cases.gst_module.portal.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CancelEWayDec {
    @JsonProperty("Irn")
    private String ewayBillNo;
    @JsonProperty("CnlRsn")
    private float cnlRsn;
    @JsonProperty("CnlRem")
    private String cnlRem;
}

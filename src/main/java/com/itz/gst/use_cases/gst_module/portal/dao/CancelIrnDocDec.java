package com.itz.gst.use_cases.gst_module.portal.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CancelIrnDocDec {
    @JsonProperty("Irn")
    private String irn;
    @JsonProperty("CnlRsn")
    private String cnlRsn;
    @JsonProperty("CnlRem")
    private String cnlRem;
}

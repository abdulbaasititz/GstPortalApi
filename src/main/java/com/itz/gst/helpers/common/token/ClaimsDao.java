package com.itz.gst.helpers.common.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimsDao {
    private String sub;
    private String usr;
    private String gst;
    private String plt;
    private String iat;
    private String exp;
}

package com.itz.gst.helpers.common.token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimsDao {
    private String iat;
    private String sub;
    private String exp;
    private String rol;
    private String usr;
    private String plt;
}

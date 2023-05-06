package com.itz.gst.helpers.common.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultDao {
    private Object results;
    private String message;
    private Boolean status;
}

package com.itz.gst.helpers.exception.custom;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private String message;
    private Date timestamp;
    private Boolean isError;

    public NotFoundException(String message) {
        super();
        this.message = message;
        this.timestamp = new Date();
        this.isError = true;
    }
}

package com.itz.gst.helpers.common.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultsDao {
    private Object results;
    private String message;
    private Boolean status;
    private int start;
    private int limit;
    private Long totalCount;

    public ResultsDao(Object results, int start, int limit, Long totalCount) {
        this.results = results;
        this.message = "Success";
        this.status = true;
        this.start = start;
        this.limit = limit;
        this.totalCount = totalCount;
    }
}

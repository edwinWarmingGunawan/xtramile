package com.xtramile.util;

import java.util.List;

public class ResponseData {
    private List<?> data;
    private String message;
    private Long total;


    public ResponseData(List<?> data,String message) {
        this.data = data;
        this.message = message;
    }

    public ResponseData(List<?> data,String message,Long total) {
        this.data = data;
        this.message = message;
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }
    public String getMessage(){
        return message;
    }

    public Long getTotal(){
        return total;
    }
}

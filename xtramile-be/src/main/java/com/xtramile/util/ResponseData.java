package com.xtramile.util;

import java.util.List;

public class ResponseData {
    private List<?> data;
    private String message;
    public ResponseData(List<?> data,String message) {
        this.data = data;
        this.message = message;
    }

    public List<?> getData() {
        return data;
    }
    public String getMessage(){
        return message;
    }
}

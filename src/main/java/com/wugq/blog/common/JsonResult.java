package com.wugq.blog.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonResult implements Serializable {

    private static final long serialVersionUID = -1137477102915470508L;

    private boolean success;

    private int code;

    private Object data;

    public JsonResult() {
    }

    public JsonResult(boolean success, int code, Object data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }

    public JsonResult(boolean success) {
        this(success, 0, null);
    }

    public JsonResult(Object data) {
        this(true, 0, data);
    }

    public JsonResult(boolean success, int code) {
        this(success, code, null);
    }
}

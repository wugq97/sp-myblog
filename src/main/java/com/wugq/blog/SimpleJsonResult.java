package com.wugq.blog;

import java.io.Serializable;

public class SimpleJsonResult implements Serializable {

    private Boolean success;

    public SimpleJsonResult(boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}

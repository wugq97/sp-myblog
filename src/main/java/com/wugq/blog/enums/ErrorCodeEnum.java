package com.wugq.blog.enums;

public enum ErrorCodeEnum {

    NOUSER(10000,"无此用户"),

    EXPIRE(10001,"token过期"),

    AUTHORITY(10002,"权限不足");

    private int value;

    private String desc;

    ErrorCodeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue(){ return value; }

    public String getDesc(){return desc;}
}

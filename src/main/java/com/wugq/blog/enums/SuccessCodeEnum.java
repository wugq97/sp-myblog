package com.wugq.blog.enums;

public enum SuccessCodeEnum {
    LOGIN(100,"登录成功"),
    INFO(101,"用户信息");

    private int value;

    private String desc;

    SuccessCodeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue(){ return value; }

    public String getDesc(){return desc;}
}

package com.wugq.blog.enums;

public enum AuthEnum {

    ADMIN(1,"管理员"),

    AUTHOR(2,"作者"),

    NORMAL(3,"普通用户");

    private int value;

    private String desc;

     AuthEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue(){ return value; }
}

package com.wugq.blog;

import java.security.MessageDigest;

public class MD5Util {

    public static String getMD5String(String str) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] bytes = messageDigest.digest();
            for (byte b : bytes) {
                if (Integer.toHexString(0xFF & b).length() == 1) {
                    buffer.append("0");
                    buffer.append(Integer.toHexString(0xFF & b));
                } else {
                    buffer.append(Integer.toHexString(0xFF & b));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}

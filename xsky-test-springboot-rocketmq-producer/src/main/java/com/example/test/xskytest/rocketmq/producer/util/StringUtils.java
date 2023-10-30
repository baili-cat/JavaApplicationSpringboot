package com.example.test.xskytest.rocketmq.producer.util;

import java.io.UnsupportedEncodingException;

/**
 * @author baili
 * @date 2023年05月23日18:31
 */
public class StringUtils extends org.springframework.util.StringUtils{
    /**
     * 字节编码：默认UTF-8
     */
    private static final String CHARSET = "UTF-8";

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static boolean isBlank(String str){
        return null == str || str.trim().length() == 0;
    }

    public static boolean notBlank(String str){
        return !isBlank(str);
    }

    /**
     * String字符串转byte字节数组
     * @param str 字符串
     * @return
     */
    public static final byte[] str2ByteArr(String str) {
        try {
            return str.getBytes(CHARSET);
        } catch (UnsupportedEncodingException var2) {
            throw new RuntimeException("unsupport charset: UTF-8");
        }
    }

    /**
     * byte字节数组转String字符串
     * @param byteArr
     * @return
     */
    public static final String byteArr2Str(byte[] byteArr) {
        return byteArr2Str(byteArr, CHARSET);
    }

    /**
     * byte字节数组转String字符串，指定字符编码
     * @param byteArr
     * @param characterEncoding
     * @return
     */
    public static final String byteArr2Str(byte[] byteArr, String characterEncoding) {
        if(byteArr != null && byteArr.length != 0) {
            try {
                return new String(byteArr, characterEncoding);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("unsupport charset: " + characterEncoding);
            }
        } else {
            return null;
        }
    }
}

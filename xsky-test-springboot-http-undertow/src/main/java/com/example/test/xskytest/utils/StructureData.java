package com.example.test.xskytest.utils;

import java.util.Random;

/**
 * @author baili
 * @date 2022年03月01日3:54 下午
 */
public class StructureData {
    // 创建一个由大写和小写字母和数字组成的字符串
    private static String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String  lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private static String  numbers = "0123456789";
    //合并所有字符串
    private static String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

    private static StringBuilder per = new StringBuilder();


    //创建随机字符串生成器

    public static String accumulate(int length) {
        //防止多次调用时每次都重新new对象，减少内存占用
        if (per.length() == (length)){
            return per.toString();
        }
        StringBuilder sb = new StringBuilder();
        //创建一个Random类的对象
        Random random = new Random();
        for(int i = 0; i < length; i++) {

            //生成随机索引号
            int index = random.nextInt(alphaNumeric.length());
            // 从字符串中获取由索引 index 指定的字符
            char randomChar = alphaNumeric.charAt(index);

            // 将字符追加到字符串生成器
            sb.append(randomChar);
        }
        per = sb;
        return per.toString();
    }

}

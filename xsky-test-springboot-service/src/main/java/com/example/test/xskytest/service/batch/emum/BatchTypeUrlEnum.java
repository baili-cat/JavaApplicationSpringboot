package com.example.test.xskytest.apacheDubboService.batch.emum;


import com.example.test.xskytest.common.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;


/**
 * 批量任务类型URL枚举
 *
 * @author baili
 */
public enum BatchTypeUrlEnum {


    MYSQL_REDIS(Integer.valueOf(1), "/batch/mysql/redis/test");

    BatchTypeUrlEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final Integer code;

    private final String desc;

    private static final Map<Integer, BatchTypeUrlEnum> MAPPING;

    public Integer getCode() {
        return this.code;
    }

    public static String getDesc(Integer code) {

        return MAPPING.get(code).desc;
    }

    static {
        MAPPING = new HashMap<>((values()).length);
        for (BatchTypeUrlEnum type : values()) {
            MAPPING.put(type.code, type);
        }
    }

    public static BatchTypeUrlEnum getFromCode(Integer code) {
        return MAPPING.get(code);
    }

}
package com.example.test.xskytest.common.constants;


import com.example.test.xskytest.common.constants.ErrorCode;

public enum CustomErrorEnum implements ErrorCode{
	/********公共********/
	CUSTOM_ERROR("99999"," 自定义错误"),


	;

    private String code;
    private String msg;

    private CustomErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }


}

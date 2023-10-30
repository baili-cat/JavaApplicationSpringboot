package com.example.test.xskytest.common.exception;


import com.example.test.xskytest.common.constants.ErrorCode;

/**
 * 消息异常
 * .<br/>
 * <p>
 * Copyright: Copyright (c) 2017  zteits
 *
 * @ClassName: RocketMQException
 * @Description:
 * @version: v1.0.0
 * @author: baili
 * @date: 2022年8月6日 下午11:01:41
 */
public class CustomException extends AppException {

    private static final long serialVersionUID = 1678532454223423L;


    /**
     * 无参构造函数
     */
    public CustomException() {
        super();
    }

    public CustomException(Throwable e) {
        super(e);
    }

    public CustomException(ErrorCode errorType) {
        super(errorType);
    }

    public CustomException(ErrorCode errorCode, String... errMsg) {
        super(errorCode, errMsg);
    }

    /**
     * 封装异常
     *
     * @param errorCode
     * @param errMsg
     * @param isTransfer 是否转换异常信息，如果为false,则直接使用errMsg信息
     */
    public CustomException(ErrorCode errorCode, String errMsg, Boolean isTransfer) {
        super(errorCode, errMsg, isTransfer);
    }

    public CustomException(ErrorCode errCode, Throwable cause, String... errMsg) {
        super(errCode, cause, errMsg);
    }
}

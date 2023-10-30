/**
 * Copyright 2013-2033 Xia Jun(3979434@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.farsunset.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.example.test.xskytest.httpserver.dto;

import com.google.gson.GsonBuilder;

public final class Response<T> {
    private int code;
    private String message;
    private T data;


    public Response(Integer code,String message) {
        this.code = code;
        this.message = message;
        //this.data = data;
    }

    @Override
    public String toString() {
        //return "Response{" +
        //        "code=" + code +
        //        ", message='" + message + '\'' +
        //        ", data=" + data +
        //        '}';
        return message;
    }

    public String toJSONString(){
        return new GsonBuilder().create().toJson(this);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    //public T getData() {
    //    return data;
    //}

    //public static <T> Response<T> ok(T data) {
    //    return new Response<>(200, "ok", data);
    //}
    public static Response<Void> ok() {
        return new Response(200,"ok");
    }

    public static <T> Response<T> ok(String msg) {
        return new Response<>(200,msg);
    }


    public static Response fail(String msg) {
        return new Response<String>(500, msg);
    }

}

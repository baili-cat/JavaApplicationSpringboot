
///**
// * Copyright 2013-2033 Xia Jun(3979434@qq.com).
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *
// ***************************************************************************************
// *                                                                                     *
// *                        Website : http://www.farsunset.com                           *
// *                                                                                     *
// ***************************************************************************************
// */
package com.example.test.xskytest.httpserver.netty.handler;
import com.example.test.xskytest.httpserver.dto.Response;
import com.example.test.xskytest.httpserver.netty.annotation.NettyHttpHandler;
import com.example.test.xskytest.httpserver.netty.http.NettyHttpRequest;


@NettyHttpHandler(path = "/request/body",method = "POST")
public class RequestBodyHandler implements IFunctionHandler<String> {
    @Override
    public Response<String> execute(NettyHttpRequest request) {
        /**
         * 可以在此拿到json转成业务需要的对象
         */
        return Response.ok("TestOpsMockDATA");
    }
}

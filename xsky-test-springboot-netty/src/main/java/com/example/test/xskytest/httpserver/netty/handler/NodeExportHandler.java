//package com.example.test.xskytest.httpserver.netty.handler;
//
//import com.example.test.xskytest.httpserver.dto.Response;
//import com.example.test.xskytest.httpserver.netty.annotation.NettyHttpHandler;
//import com.example.test.xskytest.httpserver.netty.http.NettyHttpRequest;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@NettyHttpHandler(path = "/metrics", method = "GET")
//public class NodeExportHandler implements IFunctionHandler<String> {
//    private static String metrics;
//
//    static{
//        try {
//            System.out.println("#######################static执行");
//            String pathResources = "/data/baili/xskyNodeExportMock/";
//            //String pathResources = "/Users/baili/git/netty-http-server/src/main/resources/";
//            System.out.println(pathResources);
//            String filePath = pathResources + "metricsMock.txt";
//            System.out.println(filePath);
//            Path path = Paths.get(filePath);
//            Stream<String> lines = Files.lines(path);
//            metrics = lines.collect(Collectors.joining(System.lineSeparator()));
//            System.out.println(metrics);
//            lines.close();
//
//
//        } catch (
//                IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    @Override
//    public Response<String> execute(NettyHttpRequest request) {
//        System.out.println("######################HTTP执行");
//        return new Response(metrics);
//    }
//}

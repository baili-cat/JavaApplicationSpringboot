//package com.example.test.xskytest.controller.HttpSpringCloudGatewayTestCaseController;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//import reactor.netty.http.client.HttpClient;
//
///**
// * @author baili
// * @date 2023年06月13日19:51
// */
//@RestController
//@RequestMapping("/SpringCloudGateway/")
//public class HttpSpringCloudGateway {
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p.path("/springCloudTestOpsMockString").uri("http://10.10.227.19:11013/httpController/returnString"))
//                .route(p -> p.path("/springCloudTestOpsMockList").uri("http://10.10.227.19:11013/httpController/returnList"))
//                .route(p -> p.path("/error").uri("forward:/fallback"))
//                .build();
//    }
//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("fallback");
//    }
//
//
//}

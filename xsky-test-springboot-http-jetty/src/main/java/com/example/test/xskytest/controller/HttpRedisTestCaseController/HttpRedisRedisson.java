package com.example.test.xskytest.controller.HttpRedisTestCaseController;//package com.example.test.xskytest.controller.HttpRedisTestCaseController;
//
//import com.example.test.xskytest.controller.HttpMysqlTestCaseController.HttpMysql;
//import com.example.xskytest.redis.LettuceServiceImpl;
//import com.example.xskytest.redis.util.RedisTemplateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//
///**
// * @author baili
// * @date 2022年11月07日11:39
// */
//@RestController
//@RequestMapping("/redis/redisson/")
//public class HttpRedisRedisson {
//    Logger logger = LoggerFactory.getLogger(HttpMysql.class);
//
//    //直接创建实例，后面替换为redis-lettuce的包来连接redis
//    @Resource
//    //private RedissonClient redissonClient;
//
//    @GetMapping("queryKey")
//    public String queryKey(@RequestParam(value = "key", defaultValue = "xskyTest") String key) {
//        try {
//            return "查询到的key：" + key + "值为：" + lettuceService.getString(key);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}

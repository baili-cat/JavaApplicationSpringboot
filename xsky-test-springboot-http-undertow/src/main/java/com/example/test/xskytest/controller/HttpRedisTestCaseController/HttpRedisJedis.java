package com.example.test.xskytest.controller.HttpRedisTestCaseController;

import com.example.test.xskytest.redis.JedisServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author baili
 * @date 2022年11月07日11:39
 */
@RestController
@RequestMapping("/redis/jedis/")
public class HttpRedisJedis {
    Logger logger = LoggerFactory.getLogger(HttpRedisJedis.class);
    @Autowired
    private JedisServiceImpl jedisServiceImpl;
    //@GetMapping("test")
    //public String queryTest(){
    //    springJedisConfiguration.stringRedisTemplate()
    //    return "dd";
    //}

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @ApiOperation(value = "根据key查询当前应用启动时配置的redis的值，默认key为xskyTest,值为redis默认值")
    @GetMapping("queryKey")
    public String queryKey(@RequestParam(value = "key", defaultValue = "xskyTest") String key) {
        try {
            return "查询到的key：" + key + "值为：" + jedisServiceImpl.getString(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "根据key修改当前应用启动时配置的redis的值，默认key为xskyTest,值为xskyTest")
    @GetMapping("setKeyValue")
    public String setKeyValue(@RequestParam(value = "key", defaultValue = "xskyTest") String key,
                              @RequestParam(value = "value", defaultValue = "xskyTest") String value) {
        try {
            long time = 1L;
            jedisServiceImpl.setString(key, value, time);
            return "设置key：" + key + "值为：" + value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "根据key删除当前应用启动时配置的redis的值，默认key为xskyTest")
    @PostMapping("delKeyValue")
    public String delKeyValue(@RequestParam(value = "key", defaultValue = "xskyTest") String key) {
        try {
            jedisServiceImpl.delString(key);
            return "删除key：" + key + "成功";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

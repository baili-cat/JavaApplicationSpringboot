package com.example.test.xskytest.redis;

import com.example.test.xskytest.redis.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;


/**
 * @author baili
 */
@Service
@Slf4j  //日志的处理
public class JedisServiceImpl {

    @Autowired
    private JedisUtils jedisUtils;

    /**
     * 测试String
     * 根据key 查询value值
     */
    public String getString(String key) {
        Jedis jedis = jedisUtils.getJedis();
        String val = "redis默认值";
        if (!jedis.exists(key)) {
            jedis.set(key, val);
        }
        val = jedis.get(key);
        log.info(key + "值是：" + val);
        //释放资源
        jedisUtils.close(jedis);
        return val;
    }
    /**
     * 测试String
     * 根据key 查询value值
     */
    public String delString(String key) {
        Jedis jedis = jedisUtils.getJedis();
        if (jedis.exists(key)) {
            jedis.del(key);
            log.info("删除redis中key："+ key+ "成功");
        }
        //释放资源
        jedisUtils.close(jedis);
        return "删除redis中key："+ key+ "成功";
    }

    /**
     * redis添加String
     * 根据key 添加value值
     * timeout超时时间x秒;
     */
    public String setString(String key, String value, long timeout) {
        Jedis jedis = jedisUtils.getJedis();
        String result;
        //TODO 编译时会找不到方法，暂时注释掉
        //if (jedis.exists(key)) {
        //    result = jedis.set(key, value, "XX", "EX", timeout);
        //} else {
        //    result = jedis.set(key, value, "NX", "EX", timeout);
        //}
        result = jedis.set(key, value);

        //释放资源
        jedisUtils.close(jedis);
        return result;
    }

}
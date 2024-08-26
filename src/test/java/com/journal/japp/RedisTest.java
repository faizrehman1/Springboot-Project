package com.journal.japp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("email","abc@gmail.com");
        Object email = redisTemplate.opsForValue().get("name");
        int i= 0;
    }


}

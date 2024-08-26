package com.journal.japp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {


    @Bean
    public RedisTemplate configureRedis(RedisConnectionFactory factory) {


        RedisTemplate redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(StringRedisSerializer.UTF_8);
        redisTemplate.afterPropertiesSet();
     //   redisTemplate.setKeySerializer(new StringRedisSerializer());
     //   redisTemplate.setValueSerializer(new StringRedisSerializer());


        return redisTemplate;
    }


}

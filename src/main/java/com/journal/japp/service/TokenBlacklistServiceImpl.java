package com.journal.japp.service;


import com.journal.japp.filter.JwtFilter;
import com.journal.japp.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class TokenBlacklistServiceImpl implements TokenBlacklistService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private JwtUtil jwtService;

    @Override
    public void addToBlacklist(HttpServletRequest request) {
        String token = jwtService.extractTokenFromRequest(request);
        Date expiry = jwtService.extractExpiration(token);
        // Calculate the remaining time to expiration
        long expiration = expiry.getTime() - System.currentTimeMillis();
        redisTemplate.opsForValue().set(token, "blacklisted", expiration, TimeUnit.MILLISECONDS);
    }

    @Override
    public Boolean isBlacklisted(String token) {
        return redisTemplate.hasKey(token);
    }
}

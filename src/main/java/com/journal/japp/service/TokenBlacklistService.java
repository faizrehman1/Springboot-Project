package com.journal.japp.service;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenBlacklistService {

    void addToBlacklist(HttpServletRequest request);
    Boolean isBlacklisted(String token);

}

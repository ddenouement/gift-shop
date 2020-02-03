package com.giftshop.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmationToken implements Serializable {
    private Integer tokenId;
    private Integer userId;
    private String token;
    private LocalDateTime createDate;

    public ConfirmationToken(){};

    public ConfirmationToken(Integer userId) {
        this.userId = userId;
        createDate = LocalDateTime.now();
        token = UUID.randomUUID().toString();
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}

package com.giftshop.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class ConfirmationToken implements Serializable {
    private int tokenId;
    private int userId;
    private String token;
    private LocalDateTime createDate;

    public ConfirmationToken(){};

    public ConfirmationToken(int userId) {
        this.userId = userId;
        createDate = LocalDateTime.now();
        token = UUID.randomUUID().toString();
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

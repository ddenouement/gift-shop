package com.giftshop.models;

import java.io.Serializable;
import java.time.LocalDate;

public class ConfirmationToken implements Serializable {
    private int tokedId;
    private User user;
    private String token;
    private LocalDate createDate;

    public int getTokedId() {
        return tokedId;
    }

    public void setTokedId(int tokedId) {
        this.tokedId = tokedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}

package com.giftshop.repository.interfaces;

import com.giftshop.models.ConfirmationToken;

public interface IConfirmationTokenDAO {
    boolean exists(String token);
    ConfirmationToken getById(Integer id);
    ConfirmationToken getByUserId(Integer userId);
    ConfirmationToken getByToken(String token);
    int createToken(ConfirmationToken ct);
    void deleteToken(int id);
}

package com.giftshop.repository.interfaces;

import com.giftshop.models.ConfirmationToken;

public interface IConfirmationTokenDAO {
    boolean exists(String token);
    ConfirmationToken getTokenById(Integer id);
    ConfirmationToken getTokenByUserId(Integer userId);
    ConfirmationToken getTokenByToken(String token);
    int insertNewToken(ConfirmationToken ct);
    void deleteToken(int id);
}

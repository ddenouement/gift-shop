package com.giftshop.repository.interfaces;

import com.giftshop.models.ConfirmationToken;

public interface IConfirmationTokenDAO {
    boolean exists(String token);
    ConfirmationToken getTokenById(int id);
    ConfirmationToken getTokenByUserId(int userId);
    ConfirmationToken getTokenByToken(String token);
    int insertNewToken(ConfirmationToken ct);
    void deleteToken(int id);
}

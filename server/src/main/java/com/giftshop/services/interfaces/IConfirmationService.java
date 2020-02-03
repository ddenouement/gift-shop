package com.giftshop.services.interfaces;

import com.giftshop.models.ConfirmationToken;
import com.giftshop.models.User;

public interface IConfirmationService {
    ConfirmationToken generateToken(Integer userId);
    User confirmUser(String token);
    String getConfirmLink(ConfirmationToken ct);
    public String getRecoveryLink(ConfirmationToken ct);
    public boolean exists(String token);
}

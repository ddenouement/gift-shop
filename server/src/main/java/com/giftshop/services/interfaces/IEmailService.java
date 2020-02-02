package com.giftshop.services.interfaces;

import com.giftshop.models.User;

public interface IEmailService {

    void sendGreetingEmail(User receiver);
    void sendVerificationEmail(User receiver, String subject, String verificationCode);
    //void sendDeactivationEmail(User receiver);

    void sendRecoveryEmail(User user, String recoveryMail);
    void sendSuccessfulRecoveryEmail(User user);

    //void sendEmail(Mail mail, String template);
}

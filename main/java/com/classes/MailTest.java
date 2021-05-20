package com.classes;

import org.springframework.stereotype.Component;

@Component
public class MailTest implements IMailTest {
    @Override
    public boolean ValidateEmail(String Mail) {
        return true;
    }
}

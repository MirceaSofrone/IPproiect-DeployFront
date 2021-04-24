package com.hpprediction.demo.email;

import java.util.Map;

public interface EmailSender {
    void send(String to, String subject, String templatePath, Map<String, Object> model);
}

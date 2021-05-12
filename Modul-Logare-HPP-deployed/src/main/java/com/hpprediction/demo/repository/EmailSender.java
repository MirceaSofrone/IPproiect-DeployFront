package com.hpprediction.demo.repository;

import java.util.Map;

public interface EmailSender {
    void send(String to, String subject, String templatePath, Map<String, Object> model);
}

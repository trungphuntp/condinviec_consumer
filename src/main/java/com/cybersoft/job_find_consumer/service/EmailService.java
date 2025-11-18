package com.cybersoft.job_find_consumer.service;

public interface EmailService {
    void sendEmail(String to, String subject, String contentText);
}

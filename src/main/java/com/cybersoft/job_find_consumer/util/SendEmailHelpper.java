package com.cybersoft.job_find_consumer.util;

import com.cybersoft.job_find_consumer.DTO.InforEmailDTO;
import com.cybersoft.job_find_consumer.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class SendEmailHelpper {
    private final EmailService emailService;

    @Value("${email.link.register}")
    private String linkRegister;

    public void sendEmailRegister( InforEmailDTO inforEmailDTO){
        try {
            ClassPathResource resource = new ClassPathResource(linkRegister);
            String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            html = html.replace("{{fullName}}", inforEmailDTO.getFirstName());
            html = html.replace("{{email}}", inforEmailDTO.getEmail());
            html = html.replace("{{date}}", inforEmailDTO.getDateCreated());

            emailService.sendEmail(
                    inforEmailDTO.getEmail(),
                    "Bạn đã đăng ký thành công!",
                    html
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Gửi email register thất bại");
        }
    }
}

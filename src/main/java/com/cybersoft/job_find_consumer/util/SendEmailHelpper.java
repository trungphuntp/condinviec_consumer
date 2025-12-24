package com.cybersoft.job_find_consumer.util;

import com.cybersoft.job_find_consumer.DTO.InforEmailDTO;
import com.cybersoft.job_find_consumer.DTO.InforEmailSecurityDTO;
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

    @Value("${email.link.createByAd}")
    private String linkCreateByAd;

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

    public void sendEmailSecurity(InforEmailSecurityDTO inforEmailSecurityDTO){
        try {
            ClassPathResource resource = new ClassPathResource(linkCreateByAd);
            String html = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            html = html.replace("{{fullName}}", inforEmailSecurityDTO.getFirstName());
            html = html.replace("{{email}}", inforEmailSecurityDTO.getEmail());
            html = html.replace("{{password}}", inforEmailSecurityDTO.getPassword());
            html = html.replace("{{date}}", inforEmailSecurityDTO.getDateCreated());

            emailService.sendEmail(
                    inforEmailSecurityDTO.getEmail(),
                    "Bạn đã đăng ký thành công!",
                    html
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Gửi email register thất bại");
        }
    }
}

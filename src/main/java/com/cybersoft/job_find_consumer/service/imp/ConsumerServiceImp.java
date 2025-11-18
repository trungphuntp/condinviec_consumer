package com.cybersoft.job_find_consumer.service.imp;

import com.cybersoft.job_find_consumer.DTO.InforEmailDTO;
import com.cybersoft.job_find_consumer.service.ConsumerService;
import com.cybersoft.job_find_consumer.util.SendEmailHelpper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImp implements ConsumerService {
    private final ObjectMapper objectMapper;
    private final SendEmailHelpper sendEmailHelpper;


    @Override
    @KafkaListener(topics = {"register_email"}, groupId = "group-10")
    public void listenSendEmail(String message) {
      try {
        InforEmailDTO inforEmailDTO = objectMapper.readValue(message, InforEmailDTO.class);
          sendEmailHelpper.sendEmailRegister(inforEmailDTO);
      } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException(e);
      }
    }
}

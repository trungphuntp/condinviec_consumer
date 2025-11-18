package com.cybersoft.job_find_consumer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InforEmailDTO {
    private String email;
    private String firstName;
    private String dateCreated;
}

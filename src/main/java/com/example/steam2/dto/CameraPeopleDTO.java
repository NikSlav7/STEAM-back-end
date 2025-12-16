package com.example.steam2.dto;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CameraPeopleDTO {

    private LocalDateTime localDateTime;
    private LocalTime localTime;
}

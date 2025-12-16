package com.example.steam2.domains;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
public class temperature {

    @Id
    private UUID id;
    private long temperature;
    private LocalDateTime date;
}

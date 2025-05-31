package com.petshop.petshop.dto.schedule;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class ScheduleStatusDTO {
    
    @NotNull
    LocalDateTime time;

    String status;

    public ScheduleStatusDTO(LocalDateTime time, String status) {
        this.time = time;
        this.status = status;
    }

    public ScheduleStatusDTO() {

    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTime() {
        return time;
    }
}

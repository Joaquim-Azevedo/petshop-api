package com.petshop.petshop.dto.schedule;

import java.time.LocalDateTime;

public class ScheduleStatusDTO {
    
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

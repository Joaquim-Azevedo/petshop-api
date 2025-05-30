package com.petshop.petshop.dto.schedule;

import java.time.LocalDateTime;

public class ScheduleRequest {
    
    private String type;

    private LocalDateTime startTime;

    private String animal;
    
    private String ownerCpf;

    public ScheduleRequest(String type, LocalDateTime startTime, String animal, String ownerCpf) {
        this.type = type;
        this.startTime = startTime;
        this.animal = animal;
        this.ownerCpf = ownerCpf;
    }

    public ScheduleRequest() {

    }

    public String getType() {
        return type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getAnimal() {
        return animal;
    }

    public String getOwnerCpf() {
        return ownerCpf;
    }  
}

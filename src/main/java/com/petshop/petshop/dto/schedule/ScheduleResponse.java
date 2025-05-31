package com.petshop.petshop.dto.schedule;

import java.time.LocalDateTime;

import com.petshop.petshop.dto.animal.AnimalMinDTO;
import com.petshop.petshop.dto.owner.OwnerResponse;
import com.petshop.petshop.entity.JobType;
import com.petshop.petshop.entity.Schedule;
import com.petshop.petshop.entity.ScheduleStatus;

public class ScheduleResponse {
    
    private JobType job;
    private Double value;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private ScheduleStatus status;
    private AnimalMinDTO animal;
    private OwnerResponse owner;

    public ScheduleResponse(Schedule schedule) {
        job = schedule.getJob();
        value = schedule.getJobValue();
        startTime = schedule.getStartTime();
        finishTime = schedule.getFinishTime();
        status = schedule.getStatus();
        animal = new AnimalMinDTO(schedule.getAnimal());
        owner = new OwnerResponse(schedule.getOwner());
    }

    public ScheduleResponse(JobType job, Double value, LocalDateTime startTime, LocalDateTime finishTime, ScheduleStatus status, AnimalMinDTO animal, OwnerResponse owner) {
        this.job = job;
        this.value = value;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
        this.animal = animal;
        this.owner = owner;
    }

    public ScheduleResponse() {

    }

    public JobType getJob() {
        return job;
    }

    public Double getValue() {
        return value;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public AnimalMinDTO getAnimal() {
        return animal;
    }

    public OwnerResponse getOwner() {
        return owner;
    }
}

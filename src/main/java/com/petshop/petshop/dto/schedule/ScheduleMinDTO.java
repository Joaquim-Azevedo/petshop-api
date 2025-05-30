package com.petshop.petshop.dto.schedule;

import java.time.LocalDateTime;

import com.petshop.petshop.dto.animal.AnimalMinDTO;
import com.petshop.petshop.entity.JobType;
import com.petshop.petshop.entity.Schedule;
import com.petshop.petshop.entity.ScheduleStatus;

public class ScheduleMinDTO {
    
    private JobType job;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private ScheduleStatus status;
    private AnimalMinDTO animal;

    public ScheduleMinDTO(Schedule schedule) {
        job = schedule.getJob();
        startTime = schedule.getStartTime();
        finishTime = schedule.getFinishTime();
        status = schedule.getStatus();
        animal = new AnimalMinDTO(schedule.getAnimal());
    }

    public ScheduleMinDTO(JobType job, LocalDateTime startTime, LocalDateTime finishTime, ScheduleStatus status, AnimalMinDTO animal) {
        this.job = job;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
        this.animal = animal;
    }

    public ScheduleMinDTO() {

    }

    public AnimalMinDTO getAnimal() {
        return animal;
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

    public JobType getJob() {
        return job;
    }
}

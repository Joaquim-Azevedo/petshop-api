package com.petshop.petshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JobType job;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;

    @ManyToOne
    private Animal animal;
    
    @ManyToOne
    private Owner owner;

    public Schedule(Long id, JobType job, LocalDateTime startTime, LocalDateTime finishTime, ScheduleStatus status,Animal animal, Owner owner) {
        this.id = id;
        this.job = job;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
        this.animal = animal;
        this.owner = owner;
    }

    public Schedule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobType getJob() {
        return job;
    }

    public void setJob(JobType job) {
        this.job = job;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

}

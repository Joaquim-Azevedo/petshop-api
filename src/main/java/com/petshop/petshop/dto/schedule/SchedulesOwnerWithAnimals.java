package com.petshop.petshop.dto.schedule;

import java.util.List;

import com.petshop.petshop.dto.owner.OwnerDTO;

public class SchedulesOwnerWithAnimals {
    
    OwnerDTO owner;
    List<ScheduleMinDTO> schedules;

    public SchedulesOwnerWithAnimals(OwnerDTO owner, List<ScheduleMinDTO> schedules) {
        this.owner = owner;
        this.schedules = schedules;
    }

    public SchedulesOwnerWithAnimals() {

    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public List<ScheduleMinDTO> getSchedules() {
        return schedules;
    }

}

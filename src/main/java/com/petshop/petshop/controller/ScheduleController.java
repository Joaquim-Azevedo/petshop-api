package com.petshop.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.petshop.dto.schedule.ScheduleRequest;
import com.petshop.petshop.dto.schedule.ScheduleResponse;
import com.petshop.petshop.dto.schedule.ScheduleStatusDTO;
import com.petshop.petshop.dto.schedule.SchedulesOwnerWithAnimals;
import com.petshop.petshop.service.ScheduleService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    
    @GetMapping("{cpf}")
    public SchedulesOwnerWithAnimals getSchedulesByOwnerCpf(@PathVariable String cpf) {
        return scheduleService.findAllSchedulesByCpf(cpf);
    }

    @Transactional
    @PostMapping
    public ScheduleResponse addSchedule(@RequestBody ScheduleRequest request) {
        return scheduleService.addSchedule(request);
    }

    @Transactional
    @PutMapping
    public ScheduleResponse changeScheduleStatus(@RequestBody @Valid ScheduleStatusDTO dto) {
        return scheduleService.changeScheduleStatusByTime(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ScheduleResponse cancelSchedule(@PathVariable Long id) {
        return scheduleService.cancelScheduleById(id);
    }
}

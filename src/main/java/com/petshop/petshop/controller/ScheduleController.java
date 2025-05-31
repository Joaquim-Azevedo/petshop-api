package com.petshop.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SchedulesOwnerWithAnimals> getSchedulesByOwnerCpf(@PathVariable String cpf) {
        var response = scheduleService.findAllSchedulesByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ScheduleResponse> addSchedule(@RequestBody ScheduleRequest request) {
        var response = scheduleService.addSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<ScheduleResponse> changeScheduleStatus(@RequestBody @Valid ScheduleStatusDTO dto) {
        var response = scheduleService.changeScheduleStatusByTime(dto);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponse> cancelSchedule(@PathVariable Long id) {
        var response = scheduleService.cancelScheduleById(id);
        return ResponseEntity.ok(response);
    }
}

package com.petshop.petshop.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.petshop.dto.owner.OwnerDTO;
import com.petshop.petshop.dto.schedule.ScheduleMinDTO;
import com.petshop.petshop.dto.schedule.ScheduleRequest;
import com.petshop.petshop.dto.schedule.ScheduleResponse;
import com.petshop.petshop.dto.schedule.ScheduleStatusDTO;
import com.petshop.petshop.dto.schedule.SchedulesOwnerWithAnimals;
import com.petshop.petshop.entity.Animal;
import com.petshop.petshop.entity.JobType;
import com.petshop.petshop.entity.Owner;
import com.petshop.petshop.entity.Schedule;
import com.petshop.petshop.entity.ScheduleStatus;
import com.petshop.petshop.exceptions.ElementNotFound;
import com.petshop.petshop.exceptions.InvalidArgumentException;
import com.petshop.petshop.repository.AnimalRepository;
import com.petshop.petshop.repository.OwnerRepository;
import com.petshop.petshop.repository.ScheduleRepository;

@Service
public class ScheduleService {
    
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

    // Validating job by animals
    // BANHO, TOSA ==> CACHORRO | GATO
    // CONSULTAVET ==> ALL
    private void showerClippingValidation(ScheduleRequest request, Animal animal) {
        if (request.getType().toUpperCase().equals("TOSA") || request.getType().toUpperCase().equals("BANHO")) {
            switch (animal.getBreed().getType().getType()) {
            case "Cachorro":
            case "Gato":
                break;
            default:
                throw new InvalidArgumentException("Tipo de animal não aceito para o serviço");
            }
        }
    }

    private LocalDateTime timeMeasure(ScheduleRequest request) {
        switch (request.getType().toUpperCase()) {
            case "TOSA":
            case "BANHO":
                return request.getStartTime().plusMinutes(90);
            default:
                return request.getStartTime().plusMinutes(60);
        }
    }

    private void timeConflictsValidation(LocalDateTime start, LocalDateTime finish) {
        System.out.println("Verificando");
        var schedule = scheduleRepository.findConflicts(start, finish);

        if(!schedule.isEmpty()) {
            throw new RuntimeException("Horário já existente");
        }
    }

    public ScheduleResponse addSchedule(ScheduleRequest request) {
        Animal animal = animalRepository.findById(request.getAnimal())
                .orElseThrow(() -> new ElementNotFound("Animal não encontrado"));

        Owner owner = ownerRepository.findOwnerByCpf(request.getOwnerCpf())
                .orElseThrow(() -> new ElementNotFound("Dono não encontrado"));

        // VALIDATIONS
        // Generating the FinishTime based on type of the job
        LocalDateTime finishTime = timeMeasure(request);

        // Validating if the request animal is valid for the job type (BANHO, TOSA)
        showerClippingValidation(request, animal);
        
        // Validating if there is any time conflict
        timeConflictsValidation(request.getStartTime(), finishTime);

        var schedule = new Schedule(
            null,
            JobType.valueOf(request.getType().toUpperCase()),
            request.getStartTime(),
            finishTime,
            ScheduleStatus.AGENDADO,
            animal,
            owner
        );

        scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule);
    }

    public SchedulesOwnerWithAnimals findAllSchedulesByCpf(String cpf) {
        var result = scheduleRepository.findByOwnerCpf(cpf);
        var owner = ownerRepository.findOwnerByCpf(cpf)
                .orElseThrow(() -> new ElementNotFound("Dono não encontrado"));

        return new SchedulesOwnerWithAnimals(new OwnerDTO(owner), 
                result.stream().map(ScheduleMinDTO::new).toList());
    }

    public ScheduleResponse confirmScheduleByAnimalId(ScheduleStatusDTO dto) {
        var schedule = scheduleRepository.findByTime(dto.getTime())
                .orElseThrow(() -> new ElementNotFound("Agendamento não encontrado ou inexistente"));
          
        try {
            schedule.setStatus(ScheduleStatus.valueOf(dto.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Tipo de status inválido");
        }

        return new ScheduleResponse(schedule);
    }
}

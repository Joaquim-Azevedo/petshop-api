package com.petshop.petshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    // BANHO, TOSA ==> CACHORRO || GATO
    // CONSULTAVET ==> ALL
    private void validateShowerOrClipping(ScheduleRequest request, Animal animal) {
        if (request.getType().equalsIgnoreCase("TOSA") || request.getType().equalsIgnoreCase("BANHO")) {
            switch (animal.getBreed().getType().getName()) {
            case "Cachorro":
            case "Gato":
                break;
            default:
                throw new InvalidArgumentException("Tipo de animal '" + animal.getBreed().getType().getName() + "' não aceito para o serviço");
            }
        }
    }

    private LocalDateTime calculateFinishTime(ScheduleRequest request) {
        return switch (request.getType().toUpperCase()) {
            case "BANHO", "TOSA" -> request.getStartTime().plusMinutes(90);
            default -> request.getStartTime().plusMinutes(60);
        };
    }


    // Task -> improve calculate function
    private Double calculateJobValue(ScheduleRequest request) {
        return switch (request.getType().toUpperCase()) {
            case "BANHO", "TOSA" -> 120D;
            default -> 180D;
        };
    }

    private void validateTimeConflicts(LocalDateTime start, LocalDateTime finish) {
        List<Schedule> schedule = scheduleRepository.findConflicts(start, finish);

        if(!schedule.isEmpty()) {
            throw new RuntimeException("Horário já existente");
        }
    }

    public ScheduleResponse addSchedule(ScheduleRequest request) {
        Animal animal = animalRepository.findById(request.getAnimal())
                .orElseThrow(() -> new ElementNotFound("Animal não encontrado"));

        Owner owner = ownerRepository.findOwnerByCpf(request.getOwnerCpf())
                .orElseThrow(() -> new ElementNotFound("Dono não encontrado"));

        // Calculating the FinishTime based on type of the job
        LocalDateTime finishTime = calculateFinishTime(request);
        
        // Calculating jobValue base on type of the job -> improve function <-
        Double jobValue = calculateJobValue(request);
        
        // Validating if the request animal is valid for the job type (BANHO, TOSA)
        validateShowerOrClipping(request, animal);
        
        // Validating if there is any time conflict
        validateTimeConflicts(request.getStartTime(), finishTime);

        Schedule schedule = new Schedule(
            null,
            JobType.valueOf(request.getType().toUpperCase()),
            jobValue,
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
        List<Schedule> result = scheduleRepository.findByOwnerCpf(cpf);
        Owner owner = ownerRepository.findOwnerByCpf(cpf)
                .orElseThrow(() -> new ElementNotFound("Dono não encontrado"));

        return new SchedulesOwnerWithAnimals(
                new OwnerDTO(owner), 
                result.stream().map(ScheduleMinDTO::new).toList());
    }

    public ScheduleResponse changeScheduleStatusByTime(ScheduleStatusDTO dto) {
        Schedule schedule = scheduleRepository.findByTime(dto.getTime())
                .orElseThrow(() -> new ElementNotFound("Agendamento não encontrado ou inexistente"));
        
        Set<String> allowedStatuses = Set.of("CONFIRMADO", "FINALIZADO");
        if(!allowedStatuses.contains(dto.getStatus().toUpperCase())) {
            throw new InvalidArgumentException("Tipo de status inválido");
        }
        schedule.setStatus(ScheduleStatus.valueOf(dto.getStatus().toUpperCase()));

        return new ScheduleResponse(schedule);
    }

    public ScheduleResponse cancelScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ElementNotFound("Agendamento não encontrado ou inexistente"));

        schedule.setStatus(ScheduleStatus.CANCELADO);

        return new ScheduleResponse(schedule);
    }
}

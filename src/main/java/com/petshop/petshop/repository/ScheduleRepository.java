package com.petshop.petshop.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petshop.petshop.entity.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    
    @Query("SELECT s FROM Schedule s WHERE s.owner.cpf = :cpf")
    List<Schedule> findByOwnerCpf(String cpf);

    // newStartTime < existentFinishTime
    // newFinishTime > existentStartTime
    @Query("""
            SELECT s
            FROM Schedule s 
            WHERE :start < s.finishTime
            AND :finish > s.startTime
            """)
    List<Schedule> findConflicts(
            @Param("start") LocalDateTime start, 
            @Param("finish") LocalDateTime finish);

    @Query("""
            SELECT s
            FROM Schedule s
            WHERE s.startTime = :time
            """)
    Optional<Schedule> findByTime(LocalDateTime time);
    
}

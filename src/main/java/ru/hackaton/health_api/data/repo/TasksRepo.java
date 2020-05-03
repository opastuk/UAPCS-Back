package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.TasksEntity;

import java.time.LocalDate;
import java.util.List;

public interface TasksRepo extends CrudRepository<TasksEntity, Integer> {
    @Query("select e from TasksEntity e where e.doctorId = :doctorId " +
            "and e.date = :date and e.active = :active")
    List<TasksEntity> findAllByDoctorIdAndDate(int doctorId, LocalDate date, boolean active);
}

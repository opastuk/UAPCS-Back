package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hackaton.health_api.data.entities.TasksEntity;

import java.time.LocalDate;
import java.util.List;

public interface TasksRepo extends CrudRepository<TasksEntity, Integer> {
    @Query("select e from TasksEntity e where e.doctorId = :doctorId " +
            "and e.date = :date and e.active = :active")
    List<TasksEntity> findAllByDoctorIdAndDateAndActive(int doctorId, LocalDate date, boolean active);

    @Query("select e from TasksEntity e where e.patientOms = :patientOms and e.active = :active")
    List<TasksEntity> findAllByPatientOmsAndActive(int patientOms, boolean active);

    @Modifying
    @Query("update TasksEntity e set e.doctorComment = :comment, e.viewed = true where e.id = :id")
    void setDoctorComment(@Param("id") int id, @Param("comment") String comment);

    @Modifying
    @Query("update TasksEntity e set e.active = false where e.id = :id")
    void closeTask(@Param("id") int id);
}

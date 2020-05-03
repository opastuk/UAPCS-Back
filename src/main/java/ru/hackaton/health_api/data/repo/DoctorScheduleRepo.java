package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.DoctorScheduleEntity;

import java.time.LocalDate;
import java.util.List;

public interface DoctorScheduleRepo extends CrudRepository<DoctorScheduleEntity, String> {
    @Query("select e from DoctorScheduleEntity e where e.hospitalId = :id and e.workDate = :date")
    List<DoctorScheduleEntity> findByHospitalIdAAndWorkDate(int id, LocalDate date);
}

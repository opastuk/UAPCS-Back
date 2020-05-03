package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hackaton.health_api.data.entities.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends CrudRepository<DoctorEntity, Integer> {

    @Modifying
    @Query("select e from DoctorEntity e where e.id in :doctorIdList")
    Optional<List<DoctorEntity>> findAllByIdList(@Param("doctorIdList") List<Integer> doctorIdList);
}

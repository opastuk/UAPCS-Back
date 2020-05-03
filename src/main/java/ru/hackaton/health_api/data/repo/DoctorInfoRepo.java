package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hackaton.health_api.data.entities.DoctorInfoEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DoctorInfoRepo extends CrudRepository<DoctorInfoEntity, Integer> {

    @Query("select e from DoctorInfoEntity e where e.id in :doctorIdList")
    Optional<List<DoctorInfoEntity>> findAllByIdSet(@Param("doctorIdList") Set<Integer> doctorIdList);
}

package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.DoctorWorkplaceEntity;

import java.util.List;
import java.util.Optional;

public interface DoctorWorkplaceRepo extends CrudRepository<DoctorWorkplaceEntity, Integer> {

    Optional<List<DoctorWorkplaceEntity>> findByHospitalId(int id);

}

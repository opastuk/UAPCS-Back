package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.PatientInfoEntity;

public interface PatientInfoRepo extends CrudRepository<PatientInfoEntity, String> {

}

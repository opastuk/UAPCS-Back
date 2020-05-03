package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.HospitalEntity;

import java.util.List;
import java.util.Optional;

public interface HospitalRepo extends CrudRepository<HospitalEntity, Integer> {
}

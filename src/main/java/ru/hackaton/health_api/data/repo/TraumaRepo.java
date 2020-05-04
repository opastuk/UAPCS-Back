package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.TraumaEntity;

public interface TraumaRepo extends CrudRepository<TraumaEntity, Integer> {
}

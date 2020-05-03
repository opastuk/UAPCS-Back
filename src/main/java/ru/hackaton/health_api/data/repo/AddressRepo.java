package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.AddressEntity;

public interface AddressRepo extends CrudRepository<AddressEntity, Integer> {
}

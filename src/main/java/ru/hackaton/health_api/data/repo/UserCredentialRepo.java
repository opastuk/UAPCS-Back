package ru.hackaton.health_api.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.UserCredentialEntity;

public interface UserCredentialRepo extends CrudRepository<UserCredentialEntity, String> {
}

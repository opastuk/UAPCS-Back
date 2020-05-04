package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.UserInfoEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserInfoRepo extends CrudRepository<UserInfoEntity, Integer> {
    @Query("select e from UserInfoEntity e where e.id in :userIdList")
    Optional<List<UserInfoEntity>> findAllByIdSet(Set<Integer> userIdList);
}

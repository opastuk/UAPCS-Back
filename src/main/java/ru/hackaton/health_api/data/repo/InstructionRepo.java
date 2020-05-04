package ru.hackaton.health_api.data.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.hackaton.health_api.data.entities.InstructionEntity;

import java.util.List;

public interface InstructionRepo extends CrudRepository<InstructionEntity, Integer> {
    @Query("select e from InstructionEntity e where e.traumaEntity.id in :traumaId")
    List<InstructionEntity> findAllByTraumaId(int traumaId);
}

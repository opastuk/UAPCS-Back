package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.InstructionEntity;
import ru.hackaton.health_api.data.entities.TraumaEntity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InstructionDTO {
    private Integer id;
    private String description;
    private Integer traumaId;
    private String traumaDescription;

    public InstructionEntity convertToEntity() {
        return InstructionEntity.builder()
                .id(id)
                .description(description)
                .traumaEntity(new TraumaEntity(traumaId, traumaDescription))
                .build();
    }
}

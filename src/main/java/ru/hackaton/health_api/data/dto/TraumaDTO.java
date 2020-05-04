package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.TraumaEntity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TraumaDTO {
    private Integer id;
    private String description;

    public TraumaEntity convertToEntity() {
        return TraumaEntity.builder()
                .id(id)
                .description(description)
                .build();
    }
}

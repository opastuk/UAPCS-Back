package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.TraumaDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;
import static ru.hackaton.health_api.env.Constants.TRAUMA_TABLE_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = TRAUMA_TABLE_NAME, schema = SCHEMA_NAME)
public class TraumaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String description;

    public TraumaDTO convertToDto() {
        return TraumaDTO.builder()
                .id(id)
                .description(description)
                .build();
    }
}

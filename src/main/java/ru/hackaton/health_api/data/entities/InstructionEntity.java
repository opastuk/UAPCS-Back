package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.InstructionDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static ru.hackaton.health_api.env.Constants.INSTRUCTIONS_TABLE_NAME;
import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = INSTRUCTIONS_TABLE_NAME, schema = SCHEMA_NAME)
public class InstructionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "trauma_id", referencedColumnName = "id")
    private TraumaEntity traumaEntity;

    public InstructionDTO convertToDTO() {
        return InstructionDTO.builder()
                .id(id)
                .description(description)
                .traumaId(traumaEntity.getId())
                .traumaDescription(traumaEntity.getDescription())
                .build();
    }
}

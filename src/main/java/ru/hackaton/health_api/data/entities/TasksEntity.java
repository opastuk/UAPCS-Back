package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.TasksDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;
import static ru.hackaton.health_api.env.Constants.TASKS_TABLE_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = TASKS_TABLE_NAME, schema = SCHEMA_NAME)
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    private LocalDate date;
    private String description;
    private boolean active;

    @Column(name = "doctor_comment", nullable = false)
    private String doctorComment;

    private boolean viewed;

    public TasksDTO convertToDto() {
        return TasksDTO.builder()
                .id(id)
                .patientId(patientId)
                .doctorId(doctorId)
                .date(date)
                .description(description)
                .active(active)
                .doctorComment(doctorComment)
                .viewed(viewed)
                .build();
    }
}

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


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "tasks", schema = "health_api")
public class TasksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patient_oms", nullable = false)
    private Integer patientOms;

    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    private LocalDate date;
    private String description;
    private boolean active;

    public TasksDTO convertToDto() {
        return TasksDTO.builder()
                .id(id)
                .patientOms(patientOms)
                .doctorId(doctorId)
                .date(date)
                .description(description)
                .active(active)
                .build();
    }
}

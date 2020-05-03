package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@IdClass(DoctorScheduleEntityId.class)
@Table(name = "doctor_schedule", schema = "health_api")
public class DoctorScheduleEntity {
    @Id
    @Column(name = "work_date")
    private LocalDate workDate;

    @Id
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Id
    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    private boolean available;

    public DoctorScheduleDTO convertToDto() {
        return new DoctorScheduleDTO(workDate, doctorId, "", hospitalId, startTime, endTime, available);
    }
}
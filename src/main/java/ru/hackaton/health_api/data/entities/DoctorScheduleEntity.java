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

import static ru.hackaton.health_api.env.Constants.DOCTOR_SCHEDULE_TABLE_NAME;
import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@IdClass(DoctorScheduleEntityId.class)
@Table(name = DOCTOR_SCHEDULE_TABLE_NAME, schema = SCHEMA_NAME)
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
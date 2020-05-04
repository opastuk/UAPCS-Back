package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class DoctorScheduleEntityId implements Serializable {
    private LocalDate workDate;
    private Integer doctorId;
    private Integer hospitalId;
}

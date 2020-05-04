package ru.hackaton.health_api.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.hackaton.health_api.env.Constants.DATE_PATTERN;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DoctorScheduleDTO {
    private LocalDate workDate;
    private Integer doctorId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Setter
    private String doctorName;
    private Integer hospitalId;
    @JsonFormat(pattern = DATE_PATTERN)
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean available;
}
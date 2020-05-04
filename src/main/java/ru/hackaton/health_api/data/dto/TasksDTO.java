package ru.hackaton.health_api.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.TasksEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static ru.hackaton.health_api.env.Constants.DATE_PATTERN;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class TasksDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull
    private Integer patientId;

    @NotNull
    private Integer doctorId;

    @NotNull
    @JsonFormat(pattern = DATE_PATTERN)
    private LocalDate date;

    @NotEmpty
    @NotNull
    private String description;

    private boolean active = true;
    private String doctorComment;
    private boolean viewed = false;

    public TasksEntity convertToEntity() {
        return TasksEntity.builder()
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

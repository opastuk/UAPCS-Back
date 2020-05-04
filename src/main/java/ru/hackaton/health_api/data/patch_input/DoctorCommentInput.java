package ru.hackaton.health_api.data.patch_input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DoctorCommentInput {
    private Integer taskId;
    @NotNull
    @NotEmpty
    private String comment;
}

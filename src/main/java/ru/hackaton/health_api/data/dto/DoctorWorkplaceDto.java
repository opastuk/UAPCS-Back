package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DoctorWorkplaceDto {
    private Integer id;
    private Integer hospitalId;
}
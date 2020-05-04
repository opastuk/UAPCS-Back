package ru.hackaton.health_api.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private int status;
    private String message;
    private String developerMessage;
}

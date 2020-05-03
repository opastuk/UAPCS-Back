package ru.hackaton.health_api.security;

import lombok.Getter;

import static ru.hackaton.health_api.security.Permissions.CREATE_TASK;
import static ru.hackaton.health_api.security.Permissions.DELETE_TASK;
import static ru.hackaton.health_api.security.Permissions.MODIFY_TASK;
import static ru.hackaton.health_api.security.Permissions.READ;

@Getter
public enum RoleEnum {
    ADMIN(READ, MODIFY_TASK, CREATE_TASK, DELETE_TASK),
    DOCTOR(READ, MODIFY_TASK),
    PATIENT(READ, CREATE_TASK);

    private final String[] permissions;

    RoleEnum(String... permissions) {
        this.permissions = permissions;
    }
}

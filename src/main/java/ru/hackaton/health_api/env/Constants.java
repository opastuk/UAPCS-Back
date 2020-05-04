package ru.hackaton.health_api.env;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static final String SCHEMA_NAME = "health_api";
    public static final String ADDRESS_TABLE_NAME = "address";
    public static final String DOCTOR_SCHEDULE_TABLE_NAME = "doctor_schedule";
    public static final String HOSPITAL_TABLE_NAME = "hospital_info";
    public static final String INSTRUCTIONS_TABLE_NAME = "instructions";
    public static final String TASKS_TABLE_NAME = "tasks";
    public static final String TRAUMA_TABLE_NAME = "traumas";
    public static final String USER_CREDENTIAL_TABLE_NAME = "user_credential";
    public static final String USER_INFO_TABLE_NAME = "user_info";
}

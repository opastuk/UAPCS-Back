package ru.hackaton.health_api.service;

import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.TasksDTO;
import ru.hackaton.health_api.data.dto.UserInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface HealthApiClient {

    void registerUser(UserInfoDTO input);

    void registerTask(TasksDTO input);

    List<HospitalDTO> getAllHospitals();

    List<DoctorScheduleDTO> getScheduleByHospitalAndDate(int hospitalId, LocalDate date);

    List<TasksDTO> getAllByDoctorIdAndDateAndActive(int doctorId, LocalDate date, boolean active);

    List<TasksDTO> getAllByPatientIdAndActive(int patientId, boolean active);

    void setDoctorComment(int taskId, String doctorComment);

    void closeTask(int taskId);
}

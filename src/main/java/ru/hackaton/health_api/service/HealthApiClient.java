package ru.hackaton.health_api.service;

import ru.hackaton.health_api.data.dto.DoctorInfoDTO;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.PatientInfoDTO;

import java.time.LocalDate;
import java.util.List;

public interface HealthApiClient {

    void registerPatient(PatientInfoDTO entity);

    void registerDoctor(DoctorInfoDTO entity);

    List<HospitalDTO> getAllHospitals();

    List<DoctorScheduleDTO> getScheduleByHospitalAndDate(int hospitalId, LocalDate date);

}

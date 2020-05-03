package ru.hackaton.health_api.service;

import ru.hackaton.health_api.data.dto.DoctorDto;
import ru.hackaton.health_api.data.dto.HospitalDto;
import ru.hackaton.health_api.data.entities.HospitalEntity;

import java.util.List;

public interface HealthApiClient {

    List<HospitalDto> getAllHospitals();

    List<DoctorDto> getAllDoctorsByHospital(int hospitalId);

}

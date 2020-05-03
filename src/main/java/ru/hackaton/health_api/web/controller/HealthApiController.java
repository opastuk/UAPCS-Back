package ru.hackaton.health_api.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.health_api.data.dto.DoctorDto;
import ru.hackaton.health_api.data.dto.HospitalDto;
import ru.hackaton.health_api.service.HealthApiService;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/health-api")
public class HealthApiController {

    private HealthApiService service;

    public HealthApiController(HealthApiService service) {
        this.service = service;
    }

    @GetMapping("/hospitals/all")
    public List<HospitalDto> getAllHospitals() {
        return service.getAllHospitals();
    }

    @GetMapping("/doctors/by-hospital/{id}")
    public List<DoctorDto> getAllDoctorsByHospital(@PathVariable int id) {
        return service.getAllDoctorsByHospital(id);
    }
}

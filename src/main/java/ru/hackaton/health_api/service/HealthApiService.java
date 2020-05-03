package ru.hackaton.health_api.service;

import org.springframework.stereotype.Component;
import ru.hackaton.health_api.data.dto.DoctorInfoDTO;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.PatientInfoDTO;
import ru.hackaton.health_api.data.entities.DoctorScheduleEntity;
import ru.hackaton.health_api.data.entities.HospitalEntity;
import ru.hackaton.health_api.data.repo.DoctorInfoRepo;
import ru.hackaton.health_api.data.repo.DoctorScheduleRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;
import ru.hackaton.health_api.data.repo.PatientInfoRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthApiService implements HealthApiClient {

    private DoctorInfoRepo doctorInfoRepo;
    private PatientInfoRepo patientInfoRepo;
    private DoctorScheduleRepo doctorScheduleRepo;
    private HospitalRepo hospitalRepo;

    public HealthApiService(DoctorInfoRepo doctorInfoRepo,
                            PatientInfoRepo patientInfoRepo,
                            DoctorScheduleRepo doctorScheduleRepo,
                            HospitalRepo hospitalRepo) {
        this.doctorInfoRepo = doctorInfoRepo;
        this.patientInfoRepo = patientInfoRepo;
        this.doctorScheduleRepo = doctorScheduleRepo;
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public void registerPatient(PatientInfoDTO input) {
        patientInfoRepo.save(input.convertToEntity());
    }

    @Override
    public void registerDoctor(DoctorInfoDTO input) {
        doctorInfoRepo.save(input.convertToEntity());
    }

    @Override
    public List<HospitalDTO> getAllHospitals() {
        return ((List<HospitalEntity>) hospitalRepo.findAll()).stream()
                .filter(HospitalEntity::isAvailable)
                .map(HospitalEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorScheduleDTO> getScheduleByHospitalAndDate(int hospitalId, LocalDate date) {
        List<DoctorScheduleDTO> scheduleDtoList =
                doctorScheduleRepo.findByHospitalIdAAndWorkDate(hospitalId, date)
                        .get()
                        .stream()
                        .filter(DoctorScheduleEntity::isAvailable)
                        .map(DoctorScheduleEntity::convertToDto)
                        .collect(Collectors.toList());

//        Map<Integer, DoctorEntity> doctors = new HashMap<>();
//
//        scheduleDtoList.stream()
//                .map(DoctorScheduleDto::getDoctorId)
//                .forEach(id -> doctors.put(id, doctorRepo.findById(id).get()));
//
//        scheduleDtoList.forEach(dto -> dto.setDoctorName(doctors.get(dto.getDoctorId()).getName()));

        return scheduleDtoList;
    }
}

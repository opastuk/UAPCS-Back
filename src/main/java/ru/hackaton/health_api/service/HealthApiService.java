package ru.hackaton.health_api.service;

import org.springframework.stereotype.Component;
import ru.hackaton.health_api.data.dto.DoctorDto;
import ru.hackaton.health_api.data.dto.HospitalDto;
import ru.hackaton.health_api.data.entities.DoctorEntity;
import ru.hackaton.health_api.data.entities.DoctorWorkplaceEntity;
import ru.hackaton.health_api.data.entities.HospitalEntity;
import ru.hackaton.health_api.data.repo.AddressRepo;
import ru.hackaton.health_api.data.repo.DoctorRepo;
import ru.hackaton.health_api.data.repo.DoctorWorkplaceRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthApiService implements HealthApiClient {

    private AddressRepo addressRepo;
    private DoctorRepo doctorRepo;
    private DoctorWorkplaceRepo doctorWorkplaceRepo;
    private HospitalRepo hospitalRepo;

    public HealthApiService(AddressRepo addressRepo,
                            DoctorRepo doctorRepo,
                            DoctorWorkplaceRepo doctorWorkplaceRepo,
                            HospitalRepo hospitalRepo) {
        this.addressRepo = addressRepo;
        this.doctorRepo = doctorRepo;
        this.doctorWorkplaceRepo = doctorWorkplaceRepo;
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public List<HospitalDto> getAllHospitals() {
        return ((List<HospitalEntity>) hospitalRepo.findAll()).stream()
                .filter(HospitalEntity::isAvailable)
                .map(HospitalEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDto> getAllDoctorsByHospital(int hospitalId) {
        List<Integer> doctorIdList =
                doctorWorkplaceRepo.findByHospitalId(hospitalId)
                        .get()
                        .stream()
                        .filter(entity -> entity.getHospitalId() == hospitalId)
                        .map(DoctorWorkplaceEntity::getId)
                        .collect(Collectors.toList());

        return doctorRepo.findAllByIdList(doctorIdList)
                .get()
                .stream().map(DoctorEntity::convertToDto)
                .collect(Collectors.toList());
    }
}

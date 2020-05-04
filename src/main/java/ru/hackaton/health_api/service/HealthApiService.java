package ru.hackaton.health_api.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.hackaton.health_api.data.dto.DoctorInfoDTO;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.PatientInfoDTO;
import ru.hackaton.health_api.data.dto.TasksDTO;
import ru.hackaton.health_api.data.entities.DoctorScheduleEntity;
import ru.hackaton.health_api.data.entities.HospitalEntity;
import ru.hackaton.health_api.data.entities.TasksEntity;
import ru.hackaton.health_api.data.repo.DoctorInfoRepo;
import ru.hackaton.health_api.data.repo.DoctorScheduleRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;
import ru.hackaton.health_api.data.repo.PatientInfoRepo;
import ru.hackaton.health_api.data.repo.TasksRepo;
import ru.hackaton.health_api.data.repo.UserCredentialRepo;
import ru.hackaton.health_api.exceptions.MyAlreadyCreatedException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthApiService implements HealthApiClient {

    private DoctorInfoRepo doctorInfoRepo;
    private PatientInfoRepo patientInfoRepo;
    private DoctorScheduleRepo doctorScheduleRepo;
    private HospitalRepo hospitalRepo;
    private TasksRepo tasksRepo;
    private UserCredentialRepo userCredentialRepo;

    public HealthApiService(DoctorInfoRepo doctorInfoRepo,
                            PatientInfoRepo patientInfoRepo,
                            DoctorScheduleRepo doctorScheduleRepo,
                            HospitalRepo hospitalRepo,
                            TasksRepo tasksRepo,
                            UserCredentialRepo userCredentialRepo) {
        this.doctorInfoRepo = doctorInfoRepo;
        this.patientInfoRepo = patientInfoRepo;
        this.doctorScheduleRepo = doctorScheduleRepo;
        this.hospitalRepo = hospitalRepo;
        this.tasksRepo = tasksRepo;
        this.userCredentialRepo = userCredentialRepo;
    }

    private void checkUserAlreadyExists(String login) {
        if (userCredentialRepo.findById(login).isPresent()) {
            throw new MyAlreadyCreatedException("already created");
        }
    }

    @Override
    @Transactional
    public void registerPatient(PatientInfoDTO input) {
        checkUserAlreadyExists(input.getEmail());
        userCredentialRepo.save(input.convertToUserCredentialEntity());
        patientInfoRepo.save(input.convertToPatientEntity());
    }

    @Override
    @Transactional
    public void registerDoctor(DoctorInfoDTO input) {
        checkUserAlreadyExists(input.getEmail());
        userCredentialRepo.save(input.convertToUserCredentialEntity());
        doctorInfoRepo.save(input.convertToDoctorInfoEntity());
    }

    @Override
    public void registerTask(TasksDTO input) {
        tasksRepo.save(input.convertToEntity());
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

    @Override
    public List<TasksDTO> getAllByDoctorIdAndDateAndActive(int doctorId, LocalDate date, boolean active) {
        return tasksRepo.findAllByDoctorIdAndDateAndActive(doctorId, date, active).stream()
                .map(TasksEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TasksDTO> getAllByPatientOmsAndActive(int patientOms, boolean active) {
        return tasksRepo.findAllByPatientOmsAndActive(patientOms, active).stream()
                .map(TasksEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setDoctorComment(int taskId, String doctorComment) {
        tasksRepo.setDoctorComment(taskId, doctorComment);
    }

    @Override
    @Transactional
    public void closeTask(int taskId) {
        tasksRepo.closeTask(taskId);
    }
}

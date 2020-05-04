package ru.hackaton.health_api.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.TasksDTO;
import ru.hackaton.health_api.data.dto.UserInfoDTO;
import ru.hackaton.health_api.data.entities.DoctorScheduleEntity;
import ru.hackaton.health_api.data.entities.HospitalEntity;
import ru.hackaton.health_api.data.entities.TasksEntity;
import ru.hackaton.health_api.data.repo.DoctorScheduleRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;
import ru.hackaton.health_api.data.repo.TasksRepo;
import ru.hackaton.health_api.data.repo.UserCredentialRepo;
import ru.hackaton.health_api.data.repo.UserInfoRepo;
import ru.hackaton.health_api.exceptions.MyAlreadyCreatedException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealthApiService implements HealthApiClient {

    private UserInfoRepo userInfoRepo;
    private DoctorScheduleRepo doctorScheduleRepo;
    private HospitalRepo hospitalRepo;
    private TasksRepo tasksRepo;
    private UserCredentialRepo userCredentialRepo;

    public HealthApiService(UserInfoRepo userInfoRepo,
                            DoctorScheduleRepo doctorScheduleRepo,
                            HospitalRepo hospitalRepo,
                            TasksRepo tasksRepo,
                            UserCredentialRepo userCredentialRepo) {
        this.userInfoRepo = userInfoRepo;
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

    private void checkTaskExists(int taskId) {
        if (!tasksRepo.findById(taskId).isPresent()) {
            throw new MyAlreadyCreatedException("task doesn't exist");
        }
    }

    @Override
    @Transactional
    public void registerUser(UserInfoDTO input) {
        checkUserAlreadyExists(input.getEmail());
        userCredentialRepo.save(input.convertToUserCredentialEntity());
        userInfoRepo.save(input.convertToUserInfoEntity());
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

        return scheduleDtoList;
    }

    @Override
    public List<TasksDTO> getAllByDoctorIdAndDateAndActive(int doctorId, LocalDate date, boolean active) {
        return tasksRepo.findAllByDoctorIdAndDateAndActive(doctorId, date, active).stream()
                .map(TasksEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TasksDTO> getAllByPatientIdAndActive(int patientId, boolean active) {
        return tasksRepo.findAllByPatientIdAndActive(patientId, active).stream()
                .map(TasksEntity::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void setDoctorComment(int taskId, String doctorComment) {
        checkTaskExists(taskId);
        tasksRepo.setDoctorComment(taskId, doctorComment);
    }

    @Override
    @Transactional
    public void closeTask(int taskId) {
        checkTaskExists(taskId);
        tasksRepo.closeTask(taskId);
    }
}

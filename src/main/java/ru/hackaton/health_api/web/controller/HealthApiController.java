package ru.hackaton.health_api.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.InstructionDTO;
import ru.hackaton.health_api.data.dto.TasksDTO;
import ru.hackaton.health_api.data.dto.TraumaDTO;
import ru.hackaton.health_api.data.dto.UserCredentialDTO;
import ru.hackaton.health_api.data.dto.UserInfoDTO;
import ru.hackaton.health_api.data.patch_input.DoctorCommentInput;
import ru.hackaton.health_api.security.MyUserDetailsService;
import ru.hackaton.health_api.security.Permissions;
import ru.hackaton.health_api.service.HealthApiService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static ru.hackaton.health_api.env.Constants.dateTimeFormatter;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/health-api")
public class HealthApiController {

    private final HealthApiService healthApiService;
    private final MyUserDetailsService userDetailsService;

    public HealthApiController(HealthApiService healthApiService,
                               MyUserDetailsService userDetailsService) {
        this.healthApiService = healthApiService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public UserInfoDTO login(@Valid @RequestBody UserCredentialDTO input) {
        return userDetailsService.login(input);
    }

    @PostMapping("/register/user")
    public void registerDoctor(@Valid @RequestBody UserInfoDTO input) {
        healthApiService.registerUser(input);
    }

    @PostMapping("/tasks/create")
    @Secured(Permissions.CREATE_TASK)
    public void registerTask(@Valid @RequestBody TasksDTO input) {
        healthApiService.registerTask(input);
    }

    @GetMapping("/hospitals/all")
    public List<HospitalDTO> getAllHospitals() {
        return healthApiService.getAllHospitals();
    }

    @Secured(Permissions.READ)
    @GetMapping("/schedule/by-hospital-and-date")
    public List<DoctorScheduleDTO> getAllDoctorsByHospital(
            @RequestParam(name = "hospital_id") int hospitalId,
            @RequestParam String date) {
        return healthApiService.getScheduleByHospitalAndDate
                (hospitalId, LocalDate.parse(date, dateTimeFormatter));
    }

    @Secured(Permissions.READ)
    @GetMapping("/tasks/all")
    public List<TasksDTO> getAllTasks() {
        return healthApiService.getAllTasks();
    }

    @Secured(Permissions.READ)
    @GetMapping("/tasks/by-doctor-and-date")
    public List<TasksDTO> getAllTasksByDoctorAndDateAndActive(
            @RequestParam(name = "doctor_id") int doctorId,
            @RequestParam String date,
            @RequestParam boolean active) {
        return healthApiService.getAllByDoctorIdAndDateAndActive(
                doctorId, LocalDate.parse(date, dateTimeFormatter), active);
    }

    @Secured(Permissions.READ)
    @GetMapping("/tasks/by-patient")
    public List<TasksDTO> getAllTasksByPatientAndActive(
            @RequestParam(name = "patient_id") int patientId,
            @RequestParam boolean active) {
        return healthApiService.getAllByPatientIdAndActive(patientId, active);
    }

    @Secured(Permissions.MODIFY_TASK)
    @PatchMapping("/tasks/set-comment")
    public void setDoctorComment(@Valid @RequestBody DoctorCommentInput input) {
        healthApiService.setDoctorComment(input.getTaskId(), input.getComment());
    }

    @Secured(Permissions.MODIFY_TASK)
    @PatchMapping("/tasks/close")
    public void closeTask(@RequestParam int id) {
        healthApiService.closeTask(id);
    }

    @Secured(Permissions.READ)
    @GetMapping("/traumas/all")
    public List<TraumaDTO> getAllTraumas() {
        return healthApiService.getAllTraumaList();
    }

    @Secured(Permissions.READ)
    @GetMapping("/instructions/by-trauma")
    public List<InstructionDTO> getAllInstructionsByTrauma(
            @RequestParam(name = "trauma_id") int traumaId) {
        return healthApiService.getAllInstructionsByTraumaId(traumaId);
    }
}

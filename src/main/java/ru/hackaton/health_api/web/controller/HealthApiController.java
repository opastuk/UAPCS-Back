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
import ru.hackaton.health_api.data.dto.DoctorInfoDTO;
import ru.hackaton.health_api.data.dto.DoctorScheduleDTO;
import ru.hackaton.health_api.data.dto.HospitalDTO;
import ru.hackaton.health_api.data.dto.PatientInfoDTO;
import ru.hackaton.health_api.data.dto.TasksDTO;
import ru.hackaton.health_api.data.patch_input.DoctorCommentInput;
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

    private final HealthApiService service;

    public HealthApiController(HealthApiService service) {
        this.service = service;
    }

    @PostMapping("/register/doctor")
    public void registerDoctor(@Valid @RequestBody DoctorInfoDTO input) {
        service.registerDoctor(input);
    }

    @PostMapping("/register/patient")
    public void registerPatient(@Valid @RequestBody PatientInfoDTO input) {
        service.registerPatient(input);
    }

    @PostMapping("/tasks/create")
    @Secured(Permissions.CREATE_TASK)
    public void registerTask(@Valid @RequestBody TasksDTO input) {
        service.registerTask(input);
    }

    @GetMapping("/hospitals/all")
    public List<HospitalDTO> getAllHospitals() {
        return service.getAllHospitals();
    }

    @Secured(Permissions.READ)
    @GetMapping("/schedule/by-hospital-and-date")
    public List<DoctorScheduleDTO> getAllDoctorsByHospital(
            @RequestParam(name = "hospital_id") int hospitalId,
            @RequestParam String date) {
        return service.getScheduleByHospitalAndDate
                (hospitalId, LocalDate.parse(date, dateTimeFormatter));
    }

    @Secured(Permissions.READ)
    @GetMapping("/tasks/by-doctor-and-date")
    public List<TasksDTO> getAllTasksByDoctorAndDateAndActive(
            @RequestParam(name = "doctor_id") int doctorId,
            @RequestParam String date,
            @RequestParam boolean active) {
        return service.getAllByDoctorIdAndDateAndActive(
                doctorId, LocalDate.parse(date, dateTimeFormatter), active);
    }

    @Secured(Permissions.READ)
    @GetMapping("/tasks/by-patient")
    public List<TasksDTO> getAllTasksByPatientAndActive(
            @RequestParam(name = "patient_oms") int patientOms,
            @RequestParam boolean active) {
        return service.getAllByPatientOmsAndActive(patientOms, active);
    }

    @Secured(Permissions.MODIFY_TASK)
    @PatchMapping("/tasks/set-comment")
    public void setDoctorComment(@Valid @RequestBody DoctorCommentInput input) {
        service.setDoctorComment(input.getTaskId(), input.getComment());
    }

    @Secured(Permissions.MODIFY_TASK)
    @PatchMapping("/tasks/close")
    public void closeTask(@RequestParam int id) {
        service.closeTask(id);
    }
}

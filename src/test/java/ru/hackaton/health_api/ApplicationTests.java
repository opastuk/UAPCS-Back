package ru.hackaton.health_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hackaton.health_api.data.repo.AddressRepo;
import ru.hackaton.health_api.data.repo.DoctorScheduleRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;
import ru.hackaton.health_api.data.repo.UserInfoRepo;

@SpringBootTest
class ApplicationTests {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserInfoRepo userInfoRepo;

    @Autowired
    HospitalRepo hospitalRepo;

    @Autowired
    DoctorScheduleRepo doctorScheduleRepo;


    @Test
    void contextLoads() {
        System.out.println();
    }

}

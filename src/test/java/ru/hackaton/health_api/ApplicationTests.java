package ru.hackaton.health_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hackaton.health_api.data.repo.AddressRepo;
import ru.hackaton.health_api.data.repo.DoctorRepo;
import ru.hackaton.health_api.data.repo.DoctorWorkplaceRepo;
import ru.hackaton.health_api.data.repo.HospitalRepo;

@SpringBootTest
class ApplicationTests {

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	HospitalRepo hospitalRepo;

	@Autowired
	DoctorWorkplaceRepo doctorWorkplaceRepo;


	@Test
	void contextLoads() {
		System.out.println();
	}

}

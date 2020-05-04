package ru.hackaton.health_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.hackaton.health_api.data.repo.InstructionRepo;
import ru.hackaton.health_api.data.repo.TraumaRepo;

@SpringBootTest
class ApplicationTests {

    @Autowired
    InstructionRepo instructionRepo;

    @Autowired
    TraumaRepo traumaRepo;


    @Test
    void contextLoads() {
        System.out.println();
    }
}

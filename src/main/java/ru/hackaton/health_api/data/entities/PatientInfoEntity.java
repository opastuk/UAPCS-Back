package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.PatientInfoDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "patient_info", schema = "health_api")
public class PatientInfoEntity {
    @Id
    @Column(name = "oms_polis", nullable = false)
    private String omsPolis;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private String address;
    private String email;

    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    public PatientInfoDTO convertToDto() {
        return PatientInfoDTO.builder()
                .omsPolis(omsPolis)
                .name(name)
                .birthDate(birthDate)
                .address(address)
                .email(email)
                .mobilePhone(mobilePhone)
                .build();
    }
}

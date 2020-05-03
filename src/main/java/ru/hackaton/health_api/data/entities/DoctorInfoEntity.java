package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.DoctorInfoDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "doctor_info", schema = "health_api")
public class DoctorInfoEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    private String email;
    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @Column(name="hospital_id", nullable = false)
    private Integer hospitalId;

    public DoctorInfoDTO convertToDto(){
        return DoctorInfoDTO.builder()
                .id(id)
                .name(name)
                .birthDate(birthDate)
                .email(email)
                .mobilePhone(mobilePhone)
                .hospitalId(hospitalId)
                .build();
    }
}

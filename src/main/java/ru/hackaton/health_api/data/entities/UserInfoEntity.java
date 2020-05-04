package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.UserInfoDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;
import static ru.hackaton.health_api.env.Constants.USER_INFO_TABLE_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = USER_INFO_TABLE_NAME, schema = SCHEMA_NAME)
public class UserInfoEntity {
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

    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    @Column(name = "role_id", nullable = false)
    private Integer role;

    public UserInfoDTO convertToDto() {
        return UserInfoDTO.builder()
                .id(id)
                .name(name)
                .birthDate(birthDate)
                .email(email)
                .mobilePhone(mobilePhone)
                .hospitalId(hospitalId)
                .role(role)
                .build();
    }
}

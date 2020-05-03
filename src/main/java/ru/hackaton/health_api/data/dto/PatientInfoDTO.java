package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.PatientInfoEntity;
import ru.hackaton.health_api.data.entities.UserCredentialEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PatientInfoDTO {
    @NotNull
    @NotEmpty
    private String omsPolis;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String mobilePhone;

    public PatientInfoEntity convertToPatientEntity() {
        return PatientInfoEntity.builder()
                .omsPolis(omsPolis)
                .name(name)
                .birthDate(birthDate)
                .address(address)
                .email(email)
                .mobilePhone(mobilePhone)
                .build();
    }

    public UserCredentialEntity convertToUserCredentialEntity() {
        return UserCredentialEntity.builder()
                .email(email)
                .password(password)
                .role(2)
                .build();
    }
}

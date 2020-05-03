package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.DoctorInfoEntity;
import ru.hackaton.health_api.data.entities.UserCredentialEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class DoctorInfoDTO {
    @NotNull
    private Integer id;

    @NotEmpty
    @NotNull
    private String name;

    @NotNull
    private LocalDate birthDate;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotEmpty
    @NotNull
    private String mobilePhone;

    @NotNull
    private Integer hospitalId;

    public DoctorInfoEntity convertToDoctorInfoEntity(){
        return DoctorInfoEntity.builder()
                .id(id)
                .name(name)
                .birthDate(birthDate)
                .email(email)
                .mobilePhone(mobilePhone)
                .hospitalId(hospitalId)
                .build();
    }

    public UserCredentialEntity convertToUserCredentialEntity(){
        return UserCredentialEntity.builder()
                .email(email)
                .password(password)
                .role(1)
                .build();
    }
}

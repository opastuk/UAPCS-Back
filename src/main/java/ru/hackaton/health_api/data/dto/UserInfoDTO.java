package ru.hackaton.health_api.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.entities.UserCredentialEntity;
import ru.hackaton.health_api.data.entities.UserInfoEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoDTO {
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
    private Integer role;

    @NotNull
    private Integer hospitalId;

    public UserInfoEntity convertToUserInfoEntity() {
        return UserInfoEntity.builder()
                .id(id)
                .name(name)
                .birthDate(birthDate)
                .email(email)
                .mobilePhone(mobilePhone)
                .hospitalId(role != 1 ? 0 : hospitalId)
                .role(role)
                .build();
    }

    public UserCredentialEntity convertToUserCredentialEntity() {
        return UserCredentialEntity.builder()
                .email(email)
                .password(password)
                .role(role)
                .build();
    }
}

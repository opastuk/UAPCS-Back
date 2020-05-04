package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static ru.hackaton.health_api.env.Constants.SCHEMA_NAME;
import static ru.hackaton.health_api.env.Constants.USER_CREDENTIAL_TABLE_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = USER_CREDENTIAL_TABLE_NAME, schema = SCHEMA_NAME)
public class UserCredentialEntity {
    @Id
    @Column(nullable = false)
    private String email;
    private String password;
    private Integer role;
}

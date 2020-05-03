package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "user_credential", schema = "health_api")
public class UserCredentialEntity {
    @Id
    @Column(nullable = false)
    private String email;
    private String password;
    private Integer role;
}

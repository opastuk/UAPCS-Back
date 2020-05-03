package ru.hackaton.health_api.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.hackaton.health_api.data.entities.UserCredentialEntity;
import ru.hackaton.health_api.data.repo.UserCredentialRepo;

import java.util.List;
import java.util.Optional;

@Component
public final class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final UserCredentialRepo userCredentialRepo;

    public MyUserDetailsService(UserCredentialRepo userCredentialRepo) {
        this.userCredentialRepo = userCredentialRepo;
    }

    @Override
    public final UserDetails loadUserByUsername(final String username) {
        Optional<UserCredentialEntity> user = userCredentialRepo.findById(username);
        if (!user.isPresent()) {
            throw new BadCredentialsException("Invalid user");
        }

        RoleEnum role = determineRoleById(user.get().getRole());
        List<GrantedAuthority> auths = AuthorityUtils.createAuthorityList(role.getPermissions());
        return new User(username, encoder.encode(user.get().getPassword()), auths);

    }

    private RoleEnum determineRoleById(int roleId) {
        if (roleId == 1) {
            return RoleEnum.DOCTOR;
        }
        return RoleEnum.PATIENT;
    }
}

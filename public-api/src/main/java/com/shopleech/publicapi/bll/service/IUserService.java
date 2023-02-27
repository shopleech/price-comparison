package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
public interface IUserService {

    UserTokenDTO register(UserRegisterDTO request);

    // UserTokenDTO login(UserLoginDTO request);

    @PreAuthorize("hasRole('USER')")
    User readUser(Long id);

    @PreAuthorize("hasRole('USER')")
    List<User> findUsers();

    @PreAuthorize("hasRole('ADMIN')")
    User post(User user);
}

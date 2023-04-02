package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
public interface IUserService extends UserDetailsService {

    //UserBLLDTO createUser(UserBLLDTO userBLLDTO) throws Exception;
    UserBLLDTO getUser(String email);
    UserBLLDTO getUserByUserId(String userId);
    UserBLLDTO updateUser(UserBLLDTO userBLLDTO);
    void deleteUser(String userId);
    List<UserBLLDTO> getUsers(int page, int limit);

    UserTokenDTO register(UserRegisterDTO request) throws Exception;

    // UserTokenDTO login(UserLoginDTO request);

    User readUser(Long id);

    List<UserBLLDTO> findUsers();

    User post(User user);

    User getUserByUsername(String username) throws Exception;
}

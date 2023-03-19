package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
public interface IUserService extends UserDetailsService {

    public UserBLLDTO createUser(UserBLLDTO userBLLDTO) throws Exception;
    public UserBLLDTO getUser(String email);
    public UserBLLDTO getUserByUserId(String userId);
    public UserBLLDTO updateUser(String userId,UserBLLDTO userBLLDTO);
    public void deleteUser(String userId);
    public List<UserBLLDTO> getUsers(int page, int limit);

    UserTokenDTO register(UserRegisterDTO request) throws Exception;

    // UserTokenDTO login(UserLoginDTO request);

    User readUser(Long id);

    List<UserBLLDTO> findUsers();

    User post(User user);
}

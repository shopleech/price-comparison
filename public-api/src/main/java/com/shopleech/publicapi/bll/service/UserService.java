package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.MyUserDetails;
import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.bll.util.JwtTokenUtil;
import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.bll.mapper.UserBLLMapper;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 04.02.2023
 */
@Service
public class UserService implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserBLLMapper userBLLMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserBLLDTO createUser(UserBLLDTO userBLLDTO) throws Exception {

        UserDALDTO userByEmail = userRepository.getUserByUsername(userBLLDTO.getEmail());
        if (userByEmail != null) {
            throw new RuntimeException("Record already exists");
        }

        UserDALDTO userDALDTO = userBLLMapper.mapToEntity(userBLLDTO);
        userDALDTO.setPassword(new BCryptPasswordEncoder().encode(userBLLDTO.getPassword()));
        userDALDTO.setFirstname(userBLLDTO.getFirstname());
        userDALDTO.setLastname(userBLLDTO.getLastname());
        // userDALDTO.setRoles(new ArrayList<Role>() { "USER" });

        userRepository.addUser(userDALDTO);

        return userBLLMapper.mapToDto(userDALDTO);
    }

    @Override
    public UserBLLDTO getUser(String email) {
        return null;
    }

    @Override
    public UserBLLDTO getUserByUserId(String userId) {
        return null;
    }

    @Override
    public UserBLLDTO updateUser(String userId, UserBLLDTO userBLLDTO) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<UserBLLDTO> getUsers(int page, int limit) {
        return null;
    }

    public UserTokenDTO register(UserRegisterDTO request) {

        UserBLLDTO user = new UserBLLDTO();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.addUser(userBLLMapper.mapToEntity(user));
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new UserTokenDTO(token);
    }

    @Override
    public User readUser(Long id) {
        return null;
    }

    @Override
    public List<UserBLLDTO> findUsers() {
        return userBLLMapper.mapToDto(userRepository.getAllUsers());
    }

    @Override
    public User post(User user) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        logger.info("loadUserByUsername: " + username);
        UserDALDTO user = null;
        try {
            user = userRepository.getUserByUsername(username);
            logger.info("found something");
        } catch (Exception e) {
            user = new UserDALDTO();
            user.setEmail(username);
            user.setEnabled(true);
        }

        return new MyUserDetails(userBLLMapper.mapToDto(user));
    }
}

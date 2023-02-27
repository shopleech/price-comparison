package com.shopleech.publicapi.bll.service;

import com.shopleech.publicapi.bll.MyUserDetails;
import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.bll.mapper.UserBLLMapper;
import com.shopleech.publicapi.bll.util.JwtTokenUtil;
import com.shopleech.publicapi.dal.dto.UserDALDTO;
import com.shopleech.publicapi.dal.repository.UserRepository;
import com.shopleech.publicapi.dto.v1.UserRegisterDTO;
import com.shopleech.publicapi.dto.v1.UserTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserBLLMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public UserTokenDTO register(UserRegisterDTO request) {

        UserBLLDTO user = new UserBLLDTO();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.addUser(userMapper.mapToEntity(user));
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new UserTokenDTO(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserDALDTO user = null;
        try {
            user = userRepository.getUserByUsername(username);
        } catch (Exception e) {
            user = new UserDALDTO();
            user.setEmail(username);
            user.setEnabled(true);
        }

        return new MyUserDetails(userMapper.mapToDto(user));
    }
}

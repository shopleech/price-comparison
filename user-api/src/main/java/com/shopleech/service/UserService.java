package com.shopleech.service;

import com.shopleech.domain.User;
import com.shopleech.repository.IUserRepository;
import com.shopleech.service.dto.UserBllConverter;
import com.shopleech.service.dto.UserBllDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserBllConverter userBllConverter;

    @Override
    public UserBllDto getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return userBllConverter.convertEntityToDto(new User("", ""));
    }

    public List<UserBllDto> getUsers() {

        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(userBllConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(UUID id) {
        boolean isUser = userRepository.existsById(id);
        if (!isUser) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserBllDto addUser(UserBllDto userDto) {
        User user = userBllConverter.convertDtoToEntity(userDto);

        return userBllConverter.convertEntityToDto(
                userRepository.save(user));
    }

    @Override
    public User updateUser(User user, UUID id) {
        boolean isUser = userRepository.existsById(id);
        if (!isUser) {
            throw new RuntimeException("User not found");
        }
        user.setId(id);
        return userRepository.save(user);
    }
}

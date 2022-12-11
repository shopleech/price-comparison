package com.shopleech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.shopleech.dto.UserConverter;
import com.shopleech.service.IUserService;
import com.shopleech.contract.Request;
import com.shopleech.service.dto.UserBllDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class App implements RequestHandler<Request, Object> {

    @Autowired
    private IUserService userService;

    @Autowired
    UserConverter userConverter;

    @Override
    public Object handleRequest(Request request, Context context) {

        switch (request.getHttpMethod()) {
            case "GET":
                if (request.getId() == null) {
                    List<UserBllDto> users = userService.getUsers();

                    return users.stream()
                            .map(userConverter::convertEntityToDto)
                            .collect(Collectors.toList());
                } else {
                    return userConverter.convertEntityToDto(
                            userService.getUserById(request.getId()));
                }
            case "POST":
                return userConverter.convertEntityToDto(userService.addUser(
                                userConverter.convertDtoToEntity(request.getUser())));

            case "DELETE":
                userService.deleteUser(request.getId());
        }

        return null;
    }

    @Bean
    public Supplier<String> randomString() {
        return () -> UUID.randomUUID().toString();
    }
}

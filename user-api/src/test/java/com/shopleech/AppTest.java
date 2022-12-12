package com.shopleech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopleech.domain.User;
import com.shopleech.domain.Request;
import com.shopleech.dto.UserConverter;
import com.shopleech.dto.UserDto;
import com.shopleech.service.dto.UserBllConverter;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();

        UserDto userDto = new UserDto();
        userDto.setName("App should return a greeting");

        Request request = new Request();
        request.setUser(userDto);
        request.setHttpMethod("POST");

        Object response = classUnderTest.handleRequest(request, null);

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.convertValue(response, User.class);

        assertSame("App should return a greeting", user.getName());
    }
}

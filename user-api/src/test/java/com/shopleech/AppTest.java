package com.shopleech;

import com.shopleech.domain.User;
import com.shopleech.contract.Request;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    @Test public void testAppHasAGreeting() {
        App classUnderTest = new App();

        Request request = new Request();
        //request.setUser(new User("username1", "password1"));
        request.setHttpMethod("POST");

        User obj = (User) classUnderTest.handleRequest(request, null);

        assertNotNull("App should return a greeting", obj.getName());
    }
}

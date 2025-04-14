package com.olx.inventories.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testUserEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");

        User user2 = new User();
        user2.setId(1);
        user2.setName("John Doe");
        user2.setEmail("john.doe@example.com");
        user2.setPassword("password123");

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testUserToString() {
        User user = new User();
        user.setId(1);
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("securepass");

        String expected = "User(id=1, name=Jane Doe, email=jane.doe@example.com, password=securepass)";
        assertEquals(expected, user.toString());
    }
}

package com.shev.amazon_data.dao;


import com.shev.amazon_data.model.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    private static String testLogin = "test@gmail.com";
    private static String testUserName = "Test";
    private static String testUpdatedUserName = "Test1";
    private static String testPassword = "test12345";

    @Test
    public void createDeleteUser() throws Exception {
        User testUser = new User(testLogin,testUserName,testPassword);
        User user = UserDAO.createUser(testUser);
        assertNotNull(user);
        assertEquals(testLogin,user.getLogin());
        assertEquals(testUserName,user.getUser_name());
        assertEquals(testPassword,user.getPasword());
        boolean delete = UserDAO.deleteUser(user.getLogin());
        assertTrue(delete);

    }

    @Test
    public void retrieveUser() throws Exception {
        User testUser = new User(testLogin,testUserName,testPassword);
        User user = UserDAO.createUser(testUser);
        User receiveTestUser = UserDAO.retrieveUser(testUser.getLogin());
        assertNotNull(receiveTestUser);
        assertEquals(testLogin,receiveTestUser.getLogin());
        assertEquals(testUserName,receiveTestUser.getUser_name());
        assertEquals(testPassword,receiveTestUser.getPasword());
        boolean delete = UserDAO.deleteUser(user.getLogin());
        assertTrue(delete);
    }

    @Test
    public void updateUser() throws Exception {
        User testUser = new User(testLogin,testUserName,testPassword);
        UserDAO.createUser(testUser);
        testUser.setUser_name(testUpdatedUserName);
        boolean update = UserDAO.updateUser(testUser);
        assertTrue(update);
        User receiveTestUser = UserDAO.retrieveUser(testUser.getLogin());
        assertNotNull(receiveTestUser);
        assertEquals(testLogin,receiveTestUser.getLogin());
        assertEquals(testUpdatedUserName,receiveTestUser.getUser_name());
        assertEquals(testPassword,receiveTestUser.getPasword());
        boolean delete = UserDAO.deleteUser(testUser.getLogin());
        assertTrue(delete);

    }

}

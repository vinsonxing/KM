package com.hsbc.security.service;

import com.hsbc.security.util.MessageDict;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class CredentialServiceTest {
    CredentialService ss = CredentialService.getInstance();

    @Before
    public void setup() {
        ss.clear();
    }

    @Test
    public void testCreateUser() {
        ApiResult result = ss.createUser("test", "12345");
        assertEquals(State.OK, result.getState());

        // empty username test case
        result = ss.createUser("", "34567");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // empty password test case
        result = ss.createUser("user", "");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // user exist test case
        result = ss.createUser("test", "12345");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.USER_EXIST, result.getMsg());
    }

    @Test
    public void testDeleteUser() {
        ss.createUser("test", "12345");
        ApiResult result = ss.deleteUser("test");
        assertEquals(State.OK, result.getState());

        // empty username test case
        result = ss.deleteUser("");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // user not exist test case
        result = ss.deleteUser("user");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.USER_NOT_EXIST, result.getMsg());
    }

    @Test
    public void testCreateRole() {
        ApiResult result = ss.createRole("admin");
        assertEquals(State.OK, result.getState());

        // empty role name test case
        result = ss.createRole("");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // role exist test case
        result = ss.createRole("admin");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.ROLE_EXIST, result.getMsg());
    }

    @Test
    public void testDeleteRole() {
        ss.createRole("admin");
        ApiResult result = ss.deleteRole("admin");
        assertEquals(State.OK, result.getState());

        // empty role name test case
        result = ss.deleteRole("");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // role not exist test case
        result = ss.deleteRole("noexitrole");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.ROLE_NOT_EXIST, result.getMsg());
    }

    @Test
    public void testAddRoleToUser() {
        ss.createUser("test", "12345");
        ss.createRole("admin");
        ApiResult result = ss.addRoleToUser("test", "admin");
        assertEquals(State.OK, result.getState());

        // role already exists for the user
        result = ss.addRoleToUser("test", "admin");
        assertEquals(State.OK, result.getState());

        // empty username test case
        result = ss.addRoleToUser("", "admin");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // empty role name test case
        result = ss.addRoleToUser("test", "");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // user doesn't exist
        result = ss.addRoleToUser("noexistuser", "admin");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.USER_NOT_EXIST, result.getMsg());

        // role doesn't exist
        result = ss.addRoleToUser("test", "rolenotexist");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.ROLE_NOT_EXIST, result.getMsg());
    }

    @Test
    public void testAuthenticate() {
        ss.createUser("test", "12345");
        ss.authorize("test", "12345");
        ApiResult result = ss.authenticate("test", "12345");
        assertEquals(State.OK, result.getState());
        assertTrue(((String)result.getData()).length() > 0);

        // token not exist
        ss.invalidate((String)result.getData());
        result = ss.authenticate("test", "12345");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.TOKEN_NOT_EXIST, result.getMsg());

        // wrong pwd
        result = ss.authenticate("test", "wrongpwd");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.WRONG_PASSWORD, result.getMsg());

        // empty username test case
        result = ss.authenticate("", "admin");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // empty password test case
        result = ss.authenticate("test", "");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());

        // no user
        result = ss.authenticate("nouser", "12345");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.USER_NOT_EXIST, result.getMsg());


    }

    @Test
    public void invalidate() {
        ss.createUser("admin", "12345");
        ss.authorize("admin", "12345");
        ApiResult result = ss.authenticate("admin", "12345");
        String token = (String) result.getData();
        result = ss.invalidate(token);
        assertEquals(State.OK, result.getState());

        result = ss.invalidate(token);
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.TOKEN_NOT_EXIST, result.getMsg());

        // invalidate an invalid token
        ss.authorize("admin", "12345");
        ss.authenticate("admin", "12345");
        result = ss.invalidate("rO0ABXNyAB5jb20uaHNiYy5zZWN1cml0eS5lbnRpdHkuVG9rZW4H1J2pDg5udgIAA0oABmV4cGlyZUwABXJvbGVzdAAPTGphdmEvdXRpbC9TZXQ7TAAIdXNlck5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cP__________c3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAACdAAGbm9ybWFsdAAFYWRtaW54cQB-AAc");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.TOKEN_EXPIRE, result.getMsg());
    }

    @Test
    public void testCheckRole() throws UnsupportedEncodingException {
        ss.createUser("admin", "12345");
        ss.createRole("admin");
        ss.addRoleToUser("admin", "admin");
        ss.authorize("admin", "12345");
        ss.authorize("admin", "12345");
        ss.authorize("admin", "12345");
        ApiResult result = ss.authenticate("admin", "12345");
        ApiResult result1 = ss.checkRole((String)result.getData(), "admin");
        assertEquals(State.OK, result1.getState());

        ss.createRole("role-not-in-token");
        result1 = ss.checkRole((String)result.getData(), "role-not-in-token");
        assertEquals(State.OK, result1.getState());

        ApiResult result2 = ss.checkRole((String)result.getData(), "notexist");
        assertEquals(State.OK, result2.getState());
        assertEquals(MessageDict.ROLE_NOT_EXIST, result2.getMsg());

        ApiResult result3  = ss.checkRole("rO0ABXNyAB5jb20uaHNiYy5zZWN1cml0eS5lbnRpdHkuVG9rZW4H1J2pDg5udgIAA0oABmV4cGlyZUwABXJvbGVzdAAPTGphdmEvdXRpbC9TZXQ7TAAIdXNlck5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cP__________c3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAACdAAGbm9ybWFsdAAFYWRtaW54cQB-AAc", "admin");
        assertEquals(State.ERROR, result3.getState());
        assertEquals(MessageDict.TOKEN_EXPIRE, result3.getMsg());

        ss.createUser("norole", "12345");
        ss.createRole("somerole");
        ss.authorize("norole", "12345");
        ApiResult result4 = ss.authenticate("norole", "12345");
        result4 = ss.checkRole((String)result4.getData(), "somerole");
        assertEquals(State.ERROR, result4.getState());
        assertEquals(MessageDict.NO_ROLE_FOR_USER, result4.getMsg());
    }

    @Test
    public void testGetAllRoles() {
        ss.createUser("test", "12345");
        ss.createRole("admin");
        ss.createRole("normal");
        ss.addRoleToUser("test", "admin");
        ss.addRoleToUser("test", "normal");
        ss.authorize("test", "12345");
        ApiResult result = ss.authenticate("test", "12345");
        result = ss.getAllRoles((String)result.getData());
        assertEquals(State.OK, result.getState());
        assertEquals("[normal, admin]", result.getData().toString());

        result = ss.getAllRoles("rO0ABXNyAB5jb20uaHNiYy5zZWN1cml0eS5lbnRpdHkuVG9rZW4H1J2pDg5udgIAA0oABmV4cGlyZUwABXJvbGVzdAAPTGphdmEvdXRpbC9TZXQ7TAAIdXNlck5hbWV0ABJMamF2YS9sYW5nL1N0cmluZzt4cP__________c3IAEWphdmEudXRpbC5IYXNoU2V0ukSFlZa4tzQDAAB4cHcMAAAAED9AAAAAAAACdAAGbm9ybWFsdAAFYWRtaW54cQB-AAc");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.TOKEN_EXPIRE, result.getMsg());

        result = ss.getAllRoles("");
        assertEquals(State.ERROR, result.getState());
        assertEquals(MessageDict.INPUT_EMPTY, result.getMsg());
    }
}
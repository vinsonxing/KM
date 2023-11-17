package com.hsbc.interview.dao;

import com.hsbc.interview.exception.DbException;
import com.hsbc.interview.model.User;
import com.hsbc.interview.util.StringUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserDao {
    private Map<String, User> userStore = new ConcurrentHashMap<>();
    private Set<String> roleStore = ConcurrentHashMap.newKeySet();

    public UserDao() {
        // register an admin user
        roleStore.add("admin");
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(StringUtil.hashPassword("admin"));
        admin.setRoles(new HashSet<>(Arrays.asList("admin")));
        userStore.put("admin", admin);
    }

    public boolean login(String username, String password) throws DbException {
        checkStringPara(username, "Invalid username");
        checkStringPara(password, "Invalid password");
        User u = userStore.get(username);
        return u != null && password.equals(u.getPassword());
    }

    public void createRole(String role) throws DbException {
        checkStringPara(role, "Invalid role");
        if (!roleStore.add(role)) {
            throw new DbException("role already exists!");
        }
    }

    public void removeRole(String role) throws DbException {
        checkStringPara(role, "Invalid role");
        if (!roleStore.remove(role)) {
            throw new DbException("role doesn't exist!");
        }
    }

    public void createUser(String username, String pwd, Set<String> roles) throws DbException {
        checkStringPara(username, "Invalid username");
        checkStringPara(pwd, "Invalid password");
        if (userStore.containsKey(username)) {
            throw new DbException("username already exists");
        }
        User u = new User();
        u.setUsername(username);
        u.setPassword(pwd);
        u.setRoles(roles);
        userStore.put(username, u);
    }

    public void deleteUser(String username) throws DbException {
        checkStringPara(username, "Invalid username");
        if (!userStore.containsKey(username)) {
            throw new DbException("user doesn't exist!");
        }
        userStore.remove(username);
    }


    public User getUserByName(String username) {
        return userStore.get(username);
    }

    public Set<String> getAllUsers() {
        return userStore.keySet();
    }

    public void grantRolesToUser(String username, Set<String> roles) throws DbException {
        checkStringPara(username, "Invalid username");
        if (null == roles || roles.isEmpty()) {
            return;
        }
        User u = userStore.get(username);
        if (Objects.isNull(u)) {
            throw new DbException("No user found for given user");
        }
        if (u.getRoles() == null) {
            u.setRoles(new HashSet<>());
        }
        Optional<String> invalidRoleOpt = roles.stream().filter(r -> !roleStore.contains(r)).findAny();
        if (invalidRoleOpt.isPresent()) {
            throw new DbException("Invalid role found: " + invalidRoleOpt.get());
        }
        u.getRoles().addAll(roles);
    }

    private void checkStringPara(String value, String msg) throws DbException {
        if (StringUtil.isBlank(value)) {
            throw new DbException(StringUtil.isBlank(msg) ? "Invalid parameter" : msg);
        }
    }
}

package com.hsbc.interview.service;

import com.hsbc.interview.dao.UserDao;
import com.hsbc.interview.exception.DbException;
import com.hsbc.interview.exception.HsbcException;
import com.hsbc.interview.model.Token;
import com.hsbc.interview.model.User;
import com.hsbc.interview.util.StringUtil;
import com.hsbc.interview.util.TokenUtil;

import java.util.Set;

public class UserService {

    UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }
    public void createUser(User user) throws HsbcException {
        String pwdHash = StringUtil.hashPassword(user.getPassword());
        if (StringUtil.isEmpty(pwdHash)) {
            throw new HsbcException("Invalid password provided");
        }
         try {
             userDao.createUser(user.getUsername(), pwdHash, user.getRoles());
         } catch (DbException e) {
             throw new HsbcException(e.getMessage());
         }
    }

    public void deleteUser(String username) throws HsbcException {
        try {
            userDao.deleteUser(username);
        } catch (DbException e) {
            throw new HsbcException(e.getMessage());
        }
    }

    public void createRole(String role) throws HsbcException {
        try {
            userDao.createRole(role);
        } catch (DbException e) {
            throw new HsbcException(e.getMessage());
        }
    }

    public void deleteRole(String role) throws HsbcException {
        try {
            userDao.removeRole(role);
        } catch (DbException e) {
            throw new HsbcException(e.getMessage());
        }
    }

    public boolean checkRole(String token, String role) {
        Token tokenObj = TokenUtil.toToken(token);
        if (null == tokenObj || tokenObj.getRoles() == null) return false;
        return tokenObj.getRoles().contains(role);
    }

    public String authenticate(String username, String password) throws HsbcException {
        String pwdHash = StringUtil.hashPassword(password);
        if (StringUtil.isEmpty(pwdHash)) {
            throw new HsbcException("Invalid password provided");
        }
        User u = userDao.getUserByName(username);
        if (u == null) {
            return null;
        }
        try {
            boolean result = userDao.login(username, pwdHash);
            if (result) {
                return TokenUtil.generateToken(u);
            }
        } catch (DbException e) {
            throw new HsbcException("Failed to login");
        }

        return null;
    }

    public Set<String> getAllRoles(String username) {
        User u = userDao.getUserByName(username);
        if (u == null) {
            return null;
        }
        return u.getRoles();
    }

    public Set<String> getAllUsers() {
        return userDao.getAllUsers();
    }
}

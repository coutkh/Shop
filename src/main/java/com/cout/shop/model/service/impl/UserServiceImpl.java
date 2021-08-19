package com.cout.shop.model.service.impl;

import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.dao.impl.UserDaoImpl;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.validator.UserValidator;
import com.cout.shop.util.PasswordEncryptor;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();
    private static final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean createUser(String login, String password, String email, String role) {

        boolean isCreated = false;
        try {
            if (userDao.getUserByLogin(login).isPresent()){
                return isCreated;
            }
            isCreated = userDao.add(login, email, password, role);


            /*if (UserValidator.isLoginCorrect(login)
                    && UserValidator.isPasswordCorrect(password)
                    && UserValidator.isEmailCorrect(email))
            {
                if (userDao.getUserByLogin(login).isPresent() || userDao.getByEmail(email).isPresent()){
                    return isCreated;
                }
                Optional<String> encryptedPassword = PasswordEncryptor.encryptPassword(password);
                if (encryptedPassword.isPresent()){
                    isCreated = userDao.add(login, email, password, role);
                }
            }*/
        } /*catch (DaoException e){
            throw new ServiceException(e);
        }*/ catch (Exception e) {
            e.printStackTrace();
        }

        return isCreated;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) {
        Optional<User> user = Optional.empty();
        try {
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
                Optional<User> optionalUser = userDao.getUserByLogin(login);
                if(optionalUser.isPresent()){
                    String correctPassword = optionalUser.get().getPassword();
                    //Optional<String> passwordEncr = PasswordEncryptor.encryptPassword(password);
                    //if(passwordEncr.isPresent() && correctPassword.equals(passwordEncr.get())){
                    if(/*passwordEncr.isPresent() &&*/ correctPassword.equals(password)){
                        user = optionalUser;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}

package com.cout.shop.model.service.impl;

import com.cout.shop.model.dao.DaoException;
import com.cout.shop.model.dao.UserDao;
import com.cout.shop.model.dao.impl.UserDaoImpl;
import com.cout.shop.model.entity.User;
import com.cout.shop.model.entity.UserRole;
import com.cout.shop.model.exception.ServiceException;
import com.cout.shop.model.service.UserService;
import com.cout.shop.model.validator.UserValidator;

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
    public boolean createUser(String login, String password, String email, String role) throws ServiceException {

        boolean isCreated = false;
        try {
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password) && UserValidator.isEmailCorrect(email)) {
                if (userDao.getUserByLogin(login).isPresent()) {
                    System.out.println("Такой пользователь уже существует");
                    return isCreated;
                }
                isCreated = userDao.add(login, email, password, role);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isCreated;
    }

    @Override
    public Optional<User> authorizeUser(String login, String password) {
        Optional<User> user = Optional.empty();
        try {
            if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password)) {
                Optional<User> optionalUser = userDao.getUserByLogin(login);
                if (optionalUser.isPresent()) {
                    String correctPassword = optionalUser.get().getPassword();
                    //Optional<String> passwordEncr = PasswordEncryptor.encryptPassword(password);
                    //if(passwordEncr.isPresent() && correctPassword.equals(passwordEncr.get())){
                    if (/*passwordEncr.isPresent() &&*/ correctPassword.equals(password)) {
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

    @Override
    public void updateUser(String login, String email, String password, UserRole role) throws ServiceException {
        if (UserValidator.isLoginCorrect(login) && UserValidator.isPasswordCorrect(password) && UserValidator.isEmailCorrect(email)) {
            Optional<User> user = Optional.of(new User(login, email, password, role));
            System.out.println("updateUserServ " + user);
            try {
                userDao.updateUser(user);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
    }
}

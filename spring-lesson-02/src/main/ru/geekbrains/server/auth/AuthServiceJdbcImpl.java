package ru.geekbrains.server.auth;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.geekbrains.server.User;
import ru.geekbrains.server.persistance.UserRepository;

import java.sql.SQLException;

@Service
//@Component("authService")
public class AuthServiceJdbcImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceJdbcImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authUser(User user) {
        try {
            User usr = userRepository.findByLogin(user.getLogin());
            return usr.getId() > 0 && usr.getPassword().equals(user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

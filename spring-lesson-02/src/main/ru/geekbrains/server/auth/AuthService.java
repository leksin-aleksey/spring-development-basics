package ru.geekbrains.server.auth;

import org.springframework.stereotype.Service;
import ru.geekbrains.server.User;

public interface AuthService {

    boolean authUser(User user);
}

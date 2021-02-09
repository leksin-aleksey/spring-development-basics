package ru.geekbrains.ru.geekbrains.listeners;

import ru.geekbrains.ru.geekbrains.user.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        UserRepository userRepository = new UserRepository();
        userRepository.add("user1");
        userRepository.add("user2");
        userRepository.add("user3");
        userRepository.add("user4");
        userRepository.add("user5");

        sc.setAttribute("userRepository", userRepository);
    }
}

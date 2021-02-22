package ru.geekbrains.persist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
@ComponentScan("ru.geekbrains.persist")
public class AppConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        EntityManagerFactory emFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return emFactory;
    }
}

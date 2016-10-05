package ru.javawebinar.topjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.repository.in_memory.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.in_memory.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.web.user.AdminRestController;

/**
 * Created by ia.vorobev on 05.10.2016.
 */
@Configuration
public class Config {

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepositoryImpl();
    }

    @Bean
    public MealRepository mealRepository() {
        return new InMemoryMealRepositoryImpl();
    }

    @Bean
    public AdminRestController adminRestController() {
        return new AdminRestController();
    }
}

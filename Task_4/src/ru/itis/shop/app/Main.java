package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.UserFileRepository;
import ru.itis.shop.user.infrastructure.persistence.UserMapper;
import ru.itis.shop.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {

    private final static String USER = "aaa";
    private final static String PASSWORD = "123";
    private final static String LINK = "jdbc:postgresql://localhost:5432/postgres";
    public static void main(String[] args) {
        UserRepositoryJdbcImpl userFileRepository = new UserRepositoryJdbcImpl(LINK, USER, PASSWORD);
        UserService userService = new UserService(userFileRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}

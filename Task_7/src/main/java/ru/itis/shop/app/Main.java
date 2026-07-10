package ru.itis.shop.app;

import ru.itis.shop.infrastructure.persistence.jdbc.DriverManagerDataSource;
import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties properties = loadProperties();

        DataSource dataSource = new DriverManagerDataSource(
                properties.getProperty("jdbc:postgresql://localhost:5432/postgres"),
                properties.getProperty("123"),
                properties.getProperty("123"));

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);

        UserService userService = new UserService(userRepository);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = Main.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (inputStream == null) {
                throw new IllegalStateException("Файл application.properties не найден в ресурсах");
            }

            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Не удалось прочитать application.properties", e);
        }

        return properties;
    }
}

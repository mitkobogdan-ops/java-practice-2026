package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryJdbcImpl implements UserRepository {

    private final String url;
    private final String user;
    private final String password;

    public UserRepositoryJdbcImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(User user) {
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT id, email, password, profile_description FROM users";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User foundUser = new User(
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_description")
                );
                users.add(foundUser);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return users;
    }
}

package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    public UserFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(user.getId() + "|" +
                    user.getEmail() + "|" +
                    user.getPassword() + "|" +
                    user.getProfileDescription());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                String userFromRepository = line.toString();
                if (id.equals(userFromRepository.substring(0, 36))){
                    String[] parsedUser = userFromRepository.split("\\|");
                    return new User(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new User(null, null, null);
    }
}

package ru.itis.shop.user.application;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

        return new UserDto(user.getId(), user.getEmail(), user.getProfileDescription());
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get().getPassword().equals(password);
        } else return false;
    }

    public void updateProfileDescription(String email, String profileDescription) {
        userRepository.updateProfileDescriptionByEmail(email, profileDescription);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()))
                .collect(Collectors.toList());
    }

    public List<UserDto> getUsersByProfileDescription(String profileDescription) {
        return userRepository.findByProfileDescription(profileDescription).stream()
                .map(user -> new UserDto(user.getId(), user.getEmail(), user.getProfileDescription()))
                .collect(Collectors.toList());
    }
}

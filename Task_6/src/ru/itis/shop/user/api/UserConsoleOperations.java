package ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;

import java.util.List;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner = new Scanner(System.in);

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
    }

    public void showMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Получить информацию обо всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным описанием профиля");
        System.out.println("0. Выход");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> signUp();
            case 2 -> signIn();
            case 3 -> findUserById();
            case 4 -> updateProfileDescription();
            case 5 -> findAllUsers();
            case 6 -> findUsersByProfileDescription();
            case 0 -> {
                System.out.println("Выход из программы");
                System.exit(0);
            }
            default -> System.out.println("Неизвестный пункт меню");
        }
    }

    private void signUp() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);

        System.out.println("Регистрация прошла успешно");
    }

    private void signIn() {
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        boolean success = userService.signIn(email, password);

        System.out.println(success ? "Вход выполнен успешно" : "Неверный email или пароль");
    }

    private void findUserById() {
        System.out.println("Введите id пользователя:");
        Integer id = Integer.parseInt(scanner.nextLine());

        UserDto userDto = userService.getUserById(id);

        printUser(userDto);
    }

    private void updateProfileDescription() {
        System.out.println("Введите email пользователя:");
        String email = scanner.nextLine();
        System.out.println("Введите новое описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.updateProfileDescription(email, profileDescription);

        System.out.println("Описание профиля обновлено");
    }

    private void findAllUsers() {
        List<UserDto> users = userService.getAllUsers();

        users.forEach(this::printUser);
    }

    private void findUsersByProfileDescription() {
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        List<UserDto> users = userService.getUsersByProfileDescription(profileDescription);

        users.forEach(this::printUser);
    }

    private void printUser(UserDto userDto) {
        System.out.println("id: " + userDto.getId()
                + ", email: " + userDto.getEmail()
                + ", описание профиля: " + userDto.getProfileDescription());
    }
}

package ru.itis.shop.user.api;

import ru.itis.shop.user.application.UserService;
import ru.itis.shop.user.domain.User;

import java.util.Scanner;

public class UpdatedUserConsoleOperations extends UserConsoleOperations{
    private UserService userService;
    private final Scanner scanner;

    public UpdatedUserConsoleOperations(UserService userService) {
        super(userService);
        this.scanner = new Scanner(System.in);
        UserConsoleOperations uco = new UserConsoleOperations(userService);
    }

    protected void findById(){
        System.out.println("Введите id пользователя:");
        String id = scanner.nextLine();
        System.out.println("Введется поиск...");

        if (userService.getUserRepository().findById(id).isEmpty()) {
            System.out.println("Нет такого пользователя");
        } else System.out.println(userService.getUserRepository().findById(id).get().getEmail());

    }

    protected void changeDescription(){
        System.out.println("Введите почту пользователя, чье описание выхотите изменить:");
        String email = scanner.nextLine();
        if(!userService.getUserRepository().findByEmail(email).isEmpty()) {
            User foundUser = userService.getUserRepository().findByEmail(email).get();
            System.out.println("Введите новое описание");
            String newDisc = scanner.nextLine();
            foundUser.setProfileDescription(newDisc);
        }
        else System.out.println("Такого аккаунта не существует");
    }

    @Override
    protected void printUserMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в систему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("0. Выход");
    }

    @Override
    public void showMenu() {
        printUserMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();
            }
            case "3": {
                findById();
            }
            break;
            case "4": {
                changeDescription();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }


}

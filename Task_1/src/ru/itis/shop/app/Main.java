package ru.itis.shop.app;

import ru.itis.shop.user.api.UserConsoleOperations;
import ru.itis.shop.user.infrastructure.persistence.UserDatabaseRepository;
import ru.itis.shop.user.infrastructure.persistence.UserFileRepository;


class Main{
    static void main(String[] args) {
        UserFileRepository userFileRepository = new UserFileRepository("user.txt");
        UserDatabaseRepository userDatabaseRepository = new UserDatabaseRepository();
        UserConsoleOperations operations = new UserConsoleOperations(userFileRepository);

        while (true) {
            operations.showMenu();
        }
    }
}
package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        System.out.println("Создали табл");

        userService.saveUser("User1", "U1", (byte) 22);
        userService.saveUser("User2", "U2", (byte) 35);
        userService.saveUser("User3", "U3", (byte) 85);
        userService.saveUser("User4", "U4", (byte) 54);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
      /*






  */
        // реализуйте алгоритм здесь
    }
}

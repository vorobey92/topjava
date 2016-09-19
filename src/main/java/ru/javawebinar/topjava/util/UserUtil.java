package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ia.vorobev on 19.09.2016.
 */
public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("Ivan", "bobby@mail.ru", "123", Role.ROLE_ADMIN, Role.ROLE_USER),
            new User("Navi", "boy@mail.ru", "123", Role.ROLE_USER),
            new User("Clob", "obby@mail.ru", "123",Role.ROLE_USER),
            new User("Drop", "b@mail.ru", "123", Role.ROLE_USER)
    );
}

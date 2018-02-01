package com.jim.rest.webservices.restfulwebservices.user;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 10;
    static {
        users.add(new User(1,"Adam", new Date()));
        users.add(new User(2,"Eve", new Date()));
        users.add(new User(3,"Tom", new Date()));
        users.add(new User(4,"IAN", new Date()));
        users.add(new User(5,"Jerry", new Date()));
        users.add(new User(6,"Tommy", new Date()));
        users.add(new User(7,"Jim", new Date()));
        users.add(new User(8,"Kai", new Date()));
        users.add(new User(9,"Edison", new Date()));
        users.add(new User(10,"John", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> it = users.iterator();
        while(it.hasNext()) {
            User user = it.next();
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}

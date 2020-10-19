package com.thoughtworks.capacity.gtb.mvc.repository;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void register(User user) {
        user.setId(getUserSize());
        users.add(user);
    }

    public Optional<User> findUserByUserName(String username) {
        return users.stream().filter(cur -> cur.getUsername().equals(username)).findFirst();
    }

    private Integer getUserSize() {
        return users.size() + 1;
    }
}

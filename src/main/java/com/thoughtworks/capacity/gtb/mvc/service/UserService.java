package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordInvalidException;
import com.thoughtworks.capacity.gtb.mvc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) throws UserNameAlreadyExistsException {
        if(userRepository.findUserByUserName(user.getUsername()).isPresent()){
            throw new UserNameAlreadyExistsException();
        }
        userRepository.register(user);
    }

    public User login(String username, String password) throws UserNameOrPassWordInvalidException {
        Optional<User> userOptional = userRepository.findUserByUserName(username);
        if (!userOptional.isPresent()) {
            throw new UserNameOrPassWordInvalidException();
        }
        User user = userOptional.get();
        if(!user.getPassword().equals(password)){
            throw new UserNameOrPassWordInvalidException();
        }
        return user;
    }
}

package com.thoughtworks.capacity.gtb.mvc.controller;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameAlreadyExistsException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserNameOrPassWordInvalidException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid User user) throws UserNameAlreadyExistsException {
        userService.register(user);
    }

    @GetMapping("/login")
    public User login(@RequestParam(name = "username")
                      @NotBlank(message = "用户名不为空")
                      @Pattern(regexp = "[0-9a-zA-Z_]{3,10}$", message = "用户名不合法") String username,
                      @RequestParam(name = "password")
                      @Size(max = 12, min = 5, message = "密码不合法")
                      @NotBlank(message = "密码不为空") String password) throws UserNameOrPassWordInvalidException {
        return userService.login(username, password);
    }
}

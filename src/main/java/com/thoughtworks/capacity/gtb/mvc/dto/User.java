package com.thoughtworks.capacity.gtb.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @NotBlank(message = "用户名不为空")
    @Pattern(regexp = "[0-9a-zA-Z_]{3,10}$", message = "用户名不合法")
    private String username;

    @Size(max = 12, min = 5, message = "密码不合法")
    @NotBlank(message = "密码不为空")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;
}

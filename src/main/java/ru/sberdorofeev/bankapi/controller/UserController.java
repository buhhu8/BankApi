package ru.sberdorofeev.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.entity.UsersEntity;
import ru.sberdorofeev.bankapi.service.UserService;
import ru.sberdorofeev.bankapi.service.impl.UserServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createNewUser(@RequestBody UsersEntity entity){
        userService.insertData(entity);
    }

    @GetMapping("/{userId}")
    public UsersEntity getUsers(@PathVariable Long userId){
            return userService.getUsers(userId);
    }

}

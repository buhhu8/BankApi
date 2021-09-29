package ru.sberdorofeev.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.service.UserService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UsersDto dto){
        userService.insertUser(dto);
        return new ResponseEntity<>("User was successful created",HttpStatus.CREATED);
    }

    @GetMapping
    public Collection<UsersDto> getAllUsers(){
        return userService.getAllUsers();
    }

}

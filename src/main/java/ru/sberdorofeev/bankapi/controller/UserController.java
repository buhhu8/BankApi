package ru.sberdorofeev.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UsersDto dto){
        userServiceImpl.insertUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public Collection<UsersDto> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

}

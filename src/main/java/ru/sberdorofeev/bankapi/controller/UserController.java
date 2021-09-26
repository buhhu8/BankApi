package ru.sberdorofeev.bankapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.service.UserService;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody UsersDto dto){
        if(userService.insertUser(dto)){
          return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{userId}")
    public UsersDto getUsers(@PathVariable Long userId){
            return userService.getUser(userId);
    }

    @GetMapping
    public Collection<UsersDto> getAllUsers(){
        return userService.getAllUsers();
    }

}

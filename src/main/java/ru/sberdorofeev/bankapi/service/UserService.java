package ru.sberdorofeev.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.impl.UserRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final ModelMapper modelMapper;

    public void insertUser(UsersDto usersDto) {
        UsersEntity usersEntity = modelMapper.map(usersDto, UsersEntity.class);
        usersEntity.setCreateDateUser(LocalDate.now());
        userRepository.insertUser(usersEntity);
    }

    public List<UsersDto> getAllUsers (){
        return userRepository.getAllUsers()
                .stream()
                .map(x -> modelMapper.map(x,UsersDto.class))
                .collect(Collectors.toList());
    }
}





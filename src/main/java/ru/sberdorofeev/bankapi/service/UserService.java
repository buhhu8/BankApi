package ru.sberdorofeev.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final ModelMapper modelMapper;

    public void insertUser(UsersDto usersDto) {
        UsersEntity usersEntity = modelMapper.map(usersDto, UsersEntity.class);
        userRepository.insertData(usersEntity);
    }

    public UsersDto getUser(Long userId) {
        UsersEntity usersEntity = userRepository.getUsers(userId);
        return modelMapper.map(usersEntity, UsersDto.class);
    }

    public List<UsersDto> getAllUsers (){
        if(userRepository.getAllUsers().isEmpty()){
            return new ArrayList<>();
        }
        else {
            List<UsersEntity> usersEntityList = userRepository.getAllUsers();
            List<UsersDto> usersDtoList = new ArrayList<>();
            for(UsersEntity entity: usersEntityList){
                usersDtoList.add(modelMapper.map(entity, UsersDto.class));
            }
            return usersDtoList;
        }
    }


}


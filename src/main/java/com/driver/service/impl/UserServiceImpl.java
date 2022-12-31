package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity user1= UserEntity.builder().
                id(user.getId()).
                userId(user.getUserId()).
                firstName(user.getFirstName()).
                lastName(user.getLastName()).
                email(user.getEmail()).
                build();
        userRepository.save(user1);
        return user;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity user=userRepository.findByEmail(email);
        return UserDto.builder().
                id(user.getId()).
                userId(user.getUserId()).
                lastName(user.getLastName()).
                firstName(user.getFirstName()).
                email(user.getEmail()).build();
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity user=userRepository.findByUserId(userId);
        return UserDto.builder().
                id(user.getId()).
                userId(user.getUserId()).
                lastName(user.getLastName()).
                firstName(user.getFirstName()).
                email(user.getEmail()).build();
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity=UserEntity.builder().
                id(user.getId()).
                userId(user.getUserId()).
                lastName(user.getLastName()).
                firstName(user.getFirstName()).
                email(user.getEmail()).
                build();
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity user=userRepository.findByUserId(userId);
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntities=(List<UserEntity>)userRepository.findAll();
        List<UserDto> userDtos=new ArrayList<>();
        for(UserEntity user : userEntities){
            userDtos.add(UserDto.builder().
                    id(user.getId()).
                    userId(user.getUserId()).
                    lastName(user.getLastName()).
                    firstName(user.getFirstName()).
                    email(user.getEmail()).build());
        }
        return userDtos;
    }
}
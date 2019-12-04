package com.app.service;

import com.app.Exception.MyException;
import com.app.dto.UserDto;
import com.app.model.User;
import com.app.modelMapper.MyModelMapper;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserDto add(UserDto userDto) {

        if (userDto == null) {
            throw new MyException("User shouldn't be null");

        }
        User user = MyModelMapper.fromUserDtoToUser(userDto);
        User user1 = userRepository.save(user);
        return MyModelMapper.fromUserToUserDto(user1);
    }

    public UserDto findOne(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        return userRepository
                .findById(id)
                .map(MyModelMapper::fromUserToUserDto)
                .orElseThrow(() -> new MyException("There is no player with this id"));
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(MyModelMapper::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto deleteUser(Long id) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        UserDto userDto = userRepository.findById(id)
                .map(MyModelMapper::fromUserToUserDto)
                .orElseThrow(() -> new MyException("there is no user with this id"));
        userRepository.deleteById(id);
        return userDto;
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        if (id == null) {
            throw new MyException("id shouldn't be null");
        }
        if (userDto == null) {
            throw new MyException("user with update data shouldn't be null");
        }

        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new MyException("there is no user with this id"));

        userFromDb.setName(userDto.getName() == null ? userFromDb.getName() : userDto.getName());
        userFromDb.setAge(userDto.getAge() == null ? userFromDb.getAge() : userDto.getAge());
        userFromDb.setEmail(userDto.getEmail() == null ? userFromDb.getEmail() : userDto.getEmail());
        userFromDb.setGender(userDto.getGender() == null ? userFromDb.getGender() : userDto.getGender());
        userFromDb.setEducation(userDto.getEducation() == null ? userFromDb.getEducation() : userDto.getEducation());
        userFromDb.setRole(userDto.getRole() == null ? userFromDb.getRole() : userDto.getRole());
        return MyModelMapper.fromUserToUserDto(userRepository.save(userFromDb));

    }
}

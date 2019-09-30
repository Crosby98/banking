package com.chisw.banking.service.mapper;

import com.chisw.banking.domain.User;
import com.chisw.banking.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setSurName(userDTO.getSurName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setStatus(userDTO.getStatus().getValue());
            return user;
        }
    }

//    public UserDTO userToUserDTO(User user) {
//        if (user == null) {
//            return null;
//        } else {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(userDTO.getId());
//            userDTO.setFirstName(userDTO.getFirstName());
//            userDTO.setSurName(userDTO.getSurName());
//            userDTO.setEmail(userDTO.getEmail());
//            userDTO.setPhone(userDTO.getPhone());
//            userDTO.setStatus(userDTO.getStatus());
//            return userDTO;
//        }
}

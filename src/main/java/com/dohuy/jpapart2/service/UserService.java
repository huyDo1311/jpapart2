package com.dohuy.jpapart2.service;

import com.dohuy.jpapart2.dto.UserDTO;
import com.dohuy.jpapart2.entity.User;
import com.dohuy.jpapart2.repository.UserRepository;
import com.dohuy.jpapart2.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUser(UserRequest request){

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        UserDTO userDTO = UserDTO.toDTO(user);

        return ResponseEntity.ok(user);
    }
}

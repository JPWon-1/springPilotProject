package com.start.pilotproject.service.user;

import javax.transaction.Transactional;

import com.start.pilotproject.domain.user.User;
import com.start.pilotproject.repository.user.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // join
    @Transactional
    public User join(String email, String password) {
        User user = new User(email, password);
        return userRepository.save(user);
    }
    // login
    // findByEmail
    // findAllConnectedUser
    // insert
    // update
}

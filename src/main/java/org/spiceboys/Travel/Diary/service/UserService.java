package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
      throw new ContentNotFoundException("User not found");
        }
    }

//    public User updateUserByUsername(String username, User user) {
//        Optional<User> userOptional = userRepository.findUserByUsername(username);
//        if (userOptional.isPresent()) {
//            userOptional.get();
//        } else {
//            throw new ContentNotFoundException("User not found");
//        }
//    }
}


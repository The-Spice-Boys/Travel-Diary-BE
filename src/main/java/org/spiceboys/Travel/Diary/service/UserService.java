package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.PrivateUserDTO;
import org.spiceboys.Travel.Diary.dto.PublicUserDTO;
import org.spiceboys.Travel.Diary.dto.UserDTO;
import org.spiceboys.Travel.Diary.exception.ContentNotFoundException;
import org.spiceboys.Travel.Diary.model.User;
import org.spiceboys.Travel.Diary.repository.UserRepository;
import org.springframework.stereotype.Service;
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

    public UserDTO getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        return createUserDTO(userOptional);
    }

    public UserDTO getUserByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return createUserDTO(userOptional);
    }

    private UserDTO createUserDTO(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }

        User user = userOptional.get();
        return user.getIsPrivate()
                ? new PrivateUserDTO(true)
                : new PublicUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getBio(),
                user.getProfilePicUrl(),
                user.getIsPrivate());
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


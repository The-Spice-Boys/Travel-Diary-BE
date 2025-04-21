package org.spiceboys.Travel.Diary.service;

import org.spiceboys.Travel.Diary.dto.*;
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


    public UserDTO getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        User user = checkUserExists(userOptional);
        return createUserDTO(user);
    }

    public UserDTO updateUser(String username, PatchUserDTO patchUserDTO) {
        User originalUser = userRepository.findUserByUsername(username).orElseThrow();

        if (patchUserDTO.getUsername() != null) {
            originalUser.setUsername(patchUserDTO.getUsername());
        }

        if (patchUserDTO.getFirstName() != null) {
            originalUser.setFirstName(patchUserDTO.getFirstName());
        }

        if (patchUserDTO.getLastName() != null) {
            originalUser.setLastName(patchUserDTO.getLastName());
        }

        if (patchUserDTO.getEmail() != null) {
            originalUser.setEmail(patchUserDTO.getEmail());
        }

        if (patchUserDTO.getPassword() != null) {
            originalUser.setPassword(patchUserDTO.getPassword());
        }

        if (patchUserDTO.getBio() != null) {
            originalUser.setBio(patchUserDTO.getBio());
        }

        if (patchUserDTO.getProfilePicUrl() != null) {
            originalUser.setProfilePicUrl(patchUserDTO.getProfilePicUrl());
        }

        if (patchUserDTO.getPrivate() != null) {
            originalUser.setIsPrivate(patchUserDTO.getPrivate());
        }

        User updatedUser = userRepository.save(originalUser);
        return createAuthorisedUserDTO(updatedUser);
    }

    private User checkUserExists(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new ContentNotFoundException("User not found");
        }
        return userOptional.get();
    }

    private UserDTO createUserDTO(User user) {
        return user.getIsPrivate()
                ? new PrivateUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    true)
                : new PublicUserDTO(
                    user.getUserId(),
                    user.getUsername(),
                    user.getBio(),
                    user.getProfilePicUrl(),
                    user.getIsPrivate());
    }

    private UserDTO createAuthorisedUserDTO(User user) {
        return new AuthorisedUserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getBio(),
                user.getProfilePicUrl(),
                user.getIsPrivate(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}


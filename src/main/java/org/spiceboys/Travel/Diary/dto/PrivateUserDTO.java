package org.spiceboys.Travel.Diary.dto;

public class PrivateUserDTO extends UserDTO {
    public PrivateUserDTO(Long userId, String username, Boolean isPrivate) {
        super(userId, username, isPrivate);
    }
}

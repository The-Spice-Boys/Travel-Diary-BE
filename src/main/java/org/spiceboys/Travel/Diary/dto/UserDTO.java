package org.spiceboys.Travel.Diary.dto;

public class UserDTO {
    Long userId;
    String username;
    Boolean isPrivate;

    public UserDTO(Long userId,
                   String username,
                   Boolean isPrivate) {
        this.userId = userId;
        this.username = username;
        this.isPrivate = isPrivate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}

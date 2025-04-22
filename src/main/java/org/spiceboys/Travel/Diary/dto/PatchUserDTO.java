package org.spiceboys.Travel.Diary.dto;

public class PatchUserDTO extends AuthorisedUserDTO{
    private String password;

    public PatchUserDTO(Long userId,
                        String username,
                        String bio,
                        String profilePicUrl,
                        Boolean isPrivate,
                        String firstName,
                        String lastName,
                        String email,
                        String password) {
        super(userId, username, bio, profilePicUrl, isPrivate, firstName, lastName, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package ru.kpfu.itis.zakirov.eventme.dto;

public class UserDto {
    private String username;
    private String email;
    private String password;
    private String avatarUrl;

    public UserDto(String username, String email, String password, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}

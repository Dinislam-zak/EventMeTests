package ru.kpfu.itis.zakirov.eventme.dto;

public class UserLoginDto {
    private String username;
    private String password;

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

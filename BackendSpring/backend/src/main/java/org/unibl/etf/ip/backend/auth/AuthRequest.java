package org.unibl.etf.ip.backend.auth;


import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class AuthRequest {

    @NotBlank
    @Size(max = 45, message = "Maximum character size for username is 45!")
    private String username;

    @NotBlank
    @Size(max = 500, message = "Maximum character size for password is 500!")
    private String password;

    public AuthRequest() {}

    public AuthRequest(String username, String pw) {
        this.username = username;
        this.password = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

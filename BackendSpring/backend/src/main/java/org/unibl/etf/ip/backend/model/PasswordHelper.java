package org.unibl.etf.ip.backend.model;

public class PasswordHelper {

    private String password;

    public PasswordHelper() {
    }

    public PasswordHelper(String pw) {
        password = pw;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

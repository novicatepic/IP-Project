package org.unibl.etf.ip.backend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class PasswordWrapper {
    @NotNull(message = "id is mandatory!")
    @Max(value = 1000000, message = "id value must be less than or equal to 1000000")
    private Integer id;

    @NotBlank(message = "oldPassword is mandatory!")
    @Size(max = 256, message = "Maximum character size for oldPassword is 256!")
    private String oldPassword;

    @NotBlank(message = "newPassword is mandatory!")
    @Size(max = 256, message = "Maximum character size for newPassword is 256!")
    private String newPassword;

    @NotBlank(message = "newPassword2 is mandatory!")
    @Size(max = 256, message = "Maximum character size for newPassword2 is 256!")
    private String newPassword2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}

package org.unibl.etf.ip.backend.model;

import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

public class CodeModel {
    @NotBlank(message = "code is mandatory!")
    @Size(max = 5, min = 4, message = "Maximum character size for code is 5!")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

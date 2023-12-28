package org.unibl.etf.ip.backend.errorservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ForbiddenEntity {

    public static ResponseEntity returnForbidden() {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}

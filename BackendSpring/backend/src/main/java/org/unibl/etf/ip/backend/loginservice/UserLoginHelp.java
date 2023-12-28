package org.unibl.etf.ip.backend.loginservice;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.unibl.etf.ip.backend.model.KorisnikEntity;

public class UserLoginHelp {

    private static KorisnikEntity extractCredentials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KorisnikEntity userDetails = (KorisnikEntity) authentication.getPrincipal();
        return userDetails;
    }

    public static boolean checkUserValidity(Integer id) {
        KorisnikEntity k = extractCredentials();
        if(k.getId() != id) {
            return false;
        }
        return true;
    }

}

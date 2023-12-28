package org.unibl.etf.ip.backend.loginservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unibl.etf.ip.backend.exceptions.InvalidUsernameException;
import org.unibl.etf.ip.backend.model.KorisnikEntity;
import org.unibl.etf.ip.backend.repository.FitnessUserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private FitnessUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<KorisnikEntity> user =  userRepository.findByKorisnickoIme(username);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("No username found!");
    }
}

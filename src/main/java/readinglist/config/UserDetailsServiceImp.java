package readinglist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import readinglist.repository.ReaderRepository;
import readinglist.utils.Reader;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.*;

public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByusername(username);
        if (reader == null) {
            throw new UsernameNotFoundException(username);
        }
        reader.setPassword(createDelegatingPasswordEncoder().encode(reader.getPassword()));
        return reader;
    }
}


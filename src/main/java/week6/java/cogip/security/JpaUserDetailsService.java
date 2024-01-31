package week6.java.cogip.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import week6.java.cogip.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));

//        try {
//            return userRepository
//                    .findByUsername(username)
//                    .map(SecurityUser::new)
//                    //.orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
//                    .orElseThrow(() -> {
//                        Exception exception = new UsernameNotFoundException("Username not found " + username);
//                        exception.printStackTrace();
//                        return exception;
//                    });
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}

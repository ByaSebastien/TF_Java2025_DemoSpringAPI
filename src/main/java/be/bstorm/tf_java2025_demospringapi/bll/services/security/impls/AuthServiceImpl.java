package be.bstorm.tf_java2025_demospringapi.bll.services.security.impls;

import be.bstorm.tf_java2025_demospringapi.bll.services.security.AuthService;
import be.bstorm.tf_java2025_demospringapi.dal.repositories.UserRepository;
import be.bstorm.tf_java2025_demospringapi.dl.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User login(String login, String password) {
        User user = userRepository.findByUsernameOrEmail(login).orElseThrow();

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid username or password");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(login).orElseThrow(
                () -> new UsernameNotFoundException(login)
        );
    }
}

package be.bstorm.tf_java2025_demospringapi.bll.services.security;

import be.bstorm.tf_java2025_demospringapi.dl.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    User login(String login, String password);
}

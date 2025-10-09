package be.bstorm.tf_java2025_demospringapi.api.controllers.security;

import be.bstorm.tf_java2025_demospringapi.api.models.auth.LoginForm;
import be.bstorm.tf_java2025_demospringapi.api.models.auth.UserDto;
import be.bstorm.tf_java2025_demospringapi.api.models.auth.UserTokenDto;
import be.bstorm.tf_java2025_demospringapi.bll.services.security.AuthService;
import be.bstorm.tf_java2025_demospringapi.dl.entities.User;
import be.bstorm.tf_java2025_demospringapi.il.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginForm form
            ){

        User user = authService.login(form.login(), form.password());

        String token = jwtUtil.generateToken(user);

        UserTokenDto dto = new UserTokenDto(UserDto.fromEntity(user), token);

        return ResponseEntity.ok(dto);
    }
}

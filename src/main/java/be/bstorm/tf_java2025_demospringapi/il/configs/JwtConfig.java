package be.bstorm.tf_java2025_demospringapi.il.configs;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtConfig {

    private String secret = "Yabadabadooooooooooooooooooooooooooooooooooooooo";
    public int expireAt = 86400000;
    public SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
}

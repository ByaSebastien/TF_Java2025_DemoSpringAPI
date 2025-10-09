package be.bstorm.tf_java2025_demospringapi.api.models.auth;

public record UserTokenDto(
        UserDto user,
        String token
) {
}

package be.bstorm.tf_java2025_demospringapi.api.models.auth;

import be.bstorm.tf_java2025_demospringapi.api.models.role.RoleDto;
import be.bstorm.tf_java2025_demospringapi.dl.entities.User;

import java.util.List;

public record UserDto(
        Long id,
        String username,
        List<RoleDto> roles
) {

    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream().map(RoleDto::fromEntity).toList()
        );
    }
}

package be.bstorm.tf_java2025_demospringapi.api.models.role;

import be.bstorm.tf_java2025_demospringapi.dl.entities.Role;

public record RoleDto(
        Long id,
        String name
) {

    public static RoleDto fromEntity(Role r) {
        return new RoleDto(r.getId(), r.getName());
    }
}

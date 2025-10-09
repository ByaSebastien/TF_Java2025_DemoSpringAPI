package be.bstorm.tf_java2025_demospringapi.dl.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.*;

@Entity
@Table(name = "user_")
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = false) @ToString
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private String username;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roles);
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        List<String> perms = new ArrayList<>();
//
//        for (Role role : roles) {
//            for(String permission : role.getPermissions()) {
//                if(perms.contains(permission)){
//                    continue;
//                }
//                perms.add(permission);
//            }
//        }
//
//        return perms.stream().map(SimpleGrantedAuthority::new).toList();

        return roles.stream()
                .flatMap(role -> role.getPermissions().stream()) // combine toutes les permissions
                .distinct() // supprime les doublons
                .map(SimpleGrantedAuthority::new) // transforme en SimpleGrantedAuthority
                .toList(); // retourne une liste immuable (Java 16+)

    }
}

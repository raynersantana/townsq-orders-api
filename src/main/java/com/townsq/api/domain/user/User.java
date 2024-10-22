package com.townsq.api.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class User implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private UserRole role;

    public User(String username, String encryptedPassword, UserRole role) {
        this.username = username;
        this.password = encryptedPassword;
        this.role = role;
    }

    public User(Long id, String username, UserRole role, String password) {
        this.username = username;
        this.role = role;
        this.id = id;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_ACCOUNT_MANAGER"), new SimpleGrantedAuthority("ROLE_DEFAULT"));
        else if(this.role == UserRole.ACCOUNT_MANAGER) return List.of(new SimpleGrantedAuthority("ROLE_ACCOUNT_MANAGER"), new SimpleGrantedAuthority("ROLE_DEFAULT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_DEFAULT"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

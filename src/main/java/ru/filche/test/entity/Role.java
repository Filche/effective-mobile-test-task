package ru.filche.test.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public enum Role {
    USER {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    },
    ADMIN {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        }
    };

    public abstract Collection<? extends GrantedAuthority> getAuthorities();
}
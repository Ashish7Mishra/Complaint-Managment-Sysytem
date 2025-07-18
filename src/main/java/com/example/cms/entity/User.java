package com.example.cms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
public class User implements UserDetails { // <-- IMPLEMENTS UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role;

    // --- Original Getters and Setters ---
    // (These remain unchanged)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // --- Methods from UserDetails Interface ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This method converts our user's role String into a format Spring Security understands.
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        // This must return the encrypted password. Our existing field is already named 'password'.
        return this.password;
    }

    public void setPassword(String password) {
        // Standard setter
        this.password = password;
    }

    @Override
    public String getUsername() {
        // We are using email as the username.
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // For our project, accounts never expire.
        return true;
    }

  @Override
    public boolean isAccountNonLocked() {
        // For our project, we don't lock accounts.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // For our project, passwords never expire.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // For our project, users are always enabled.
        return true;
    }
    
    // --- Other Getters and Setters ---
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
package com.shopleech.publicapi.bll;

import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.domain.Role;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Ahto Jalak
 * @since 25.02.2023
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private UserBLLDTO user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Logger logger = LoggerFactory.getLogger(MyUserDetails.class);
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            logger.info(role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.isEnabled();
    }
}

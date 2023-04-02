package com.shopleech.publicapi.bll;

import com.shopleech.publicapi.bll.dto.RoleBLLDTO;
import com.shopleech.publicapi.bll.dto.UserBLLDTO;
import com.shopleech.publicapi.bll.dto.UserRoleBLLDTO;
import com.shopleech.publicapi.domain.Role;
import com.shopleech.publicapi.domain.User;
import com.shopleech.publicapi.domain.UserRole;
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
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Logger logger = LoggerFactory.getLogger(MyUserDetails.class);
        Set<UserRole> userRoles = user.getUserRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            Role role = userRole.getRole();
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

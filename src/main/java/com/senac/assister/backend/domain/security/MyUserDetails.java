package com.senac.assister.backend.domain.security;

import com.senac.assister.backend.domain.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    public final Customer loggedCustomer;

    public MyUserDetails(Customer loggedCustomer) {
        this.loggedCustomer = loggedCustomer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(loggedCustomer.getCustomerType().toString()));

        return list;
    }

    @Override
    public String getPassword() {
        return loggedCustomer.getPassword();
    }

    @Override
    public String getUsername() {
        return loggedCustomer.getEmail();
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

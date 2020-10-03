package com.senac.assister.backend.domain.service;

import com.senac.assister.backend.domain.model.MyUserDetails;
import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository custRepo) {
        this.customerRepository = custRepo;
    }

    // method responsible per get user and yours credentials
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Customer customer = customerRepository.findByEmailAndActiveTrue(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
            return new MyUserDetails(customer);
    }
}

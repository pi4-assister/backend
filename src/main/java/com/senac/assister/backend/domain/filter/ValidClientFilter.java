package com.senac.assister.backend.domain.filter;

import com.senac.assister.backend.domain.entity.Customer;
import com.senac.assister.backend.domain.exception.CustomerNotFoundException;
import com.senac.assister.backend.domain.repository.CustomerRepository;
import com.senac.assister.backend.domain.security.Hash;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class ValidClientFilter implements Filter {

    private final CustomerRepository customerRepository;

    public ValidClientFilter(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String URL = ((HttpServletRequest)request).getRequestURL().toString();
        final String[] PATHSNOVALID = {"Teste"};
        for(int i = 0; i < PATHSNOVALID.length; i++){
            //if(!URL.contains(PATHSNOVALID[i]))
              //  verifyUserSession(request, response, chain);
        }
        // if not down in verifyUserSession or some exception proceed request
        chain.doFilter(request, response);
    }

    private void verifyUserSession(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        // get actual session
        final HttpSession session = ((HttpServletRequest)request).getSession();
        // get user session
        final Object obj = session.getAttribute("user");
        // get email param
        final String email = request.getParameter("email");
        // get password param
        String password = request.getParameter("password");
        // convert password in hash
        password = Hash.convertToMd5(password);

        // verify user existi in actual session
        if(obj == null){
            // get costumer if email and password is correct
            final Customer costumer = customerRepository.findByEmailAndPasswordAndActiveTrue(email, password).orElseThrow(() -> new CustomerNotFoundException());
            // set user in actual session
            session.setAttribute("user", costumer);
        }else{
            Customer customer = (Customer)obj;
            // validate email and password with user session
            if(!customer.getEmail().equalsIgnoreCase(email) || !customer.getPassword().equalsIgnoreCase(password)){
                // remove user in session
                session.removeAttribute("user");
                throw new CustomerNotFoundException();
            }

        }

    }

}

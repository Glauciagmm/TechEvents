package com.techevents.techevents.security;


import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        User.UserBuilder builder = null;

        if (user != null){
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(user.getPassword());
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}

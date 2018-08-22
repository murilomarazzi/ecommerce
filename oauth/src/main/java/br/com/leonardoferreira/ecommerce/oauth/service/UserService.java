package br.com.leonardoferreira.ecommerce.oauth.service;

import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.oauth.mapper.UserMapper;
import br.com.leonardoferreira.ecommerce.oauth.repository.UserRepository;
import br.com.leonardoferreira.ecommerce.oauth.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return user;
    }

    @Transactional
    public Long create(final CreateUserRequest createUserRequest) {
        User user = userMapper.createUserToUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

        return user.getId();
    }
}

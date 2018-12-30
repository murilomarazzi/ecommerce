package br.com.leonardoferreira.ecommerce.oauth.service.impl;

import br.com.leonardoferreira.ecommerce.oauth.domain.User;
import br.com.leonardoferreira.ecommerce.oauth.domain.request.CreateUserRequest;
import br.com.leonardoferreira.ecommerce.oauth.domain.response.UserResponse;
import br.com.leonardoferreira.ecommerce.oauth.exception.ResourceNotFoundException;
import br.com.leonardoferreira.ecommerce.oauth.mapper.UserMapper;
import br.com.leonardoferreira.ecommerce.oauth.repository.UserRepository;
import br.com.leonardoferreira.ecommerce.oauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.info("Method=loadUserByUsername, username={}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
    }

    @Override
    @Transactional
    public Long create(final CreateUserRequest createUserRequest) {
        log.info("Method=create, createUserRequest={}", createUserRequest);

        User user = userMapper.createUserToUser(createUserRequest);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

        return user.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findUserInfo() {
        log.info("Method=findUserInfo");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.userToInfo(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(final Long id) {
        log.info("Method=findById, id={}", id);

        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return userMapper.userToInfo(user);
    }
}

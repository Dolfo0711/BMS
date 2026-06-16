package com.bms.projectx.service;

import com.bms.projectx.entity.UserEntity;
import com.bms.projectx.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity update(Long id, UserEntity user) {
        UserEntity existing = findById(id);

        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        existing.setBirthday(user.getBirthday());
        existing.setRole(user.getRole());

        return userRepository.save(existing);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}
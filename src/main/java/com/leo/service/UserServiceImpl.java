package com.leo.service;

import com.leo.model.User;
import com.leo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}

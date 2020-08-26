package com.leo.repository;

import com.leo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
}

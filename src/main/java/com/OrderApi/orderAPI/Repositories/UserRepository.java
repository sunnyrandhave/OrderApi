package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.Entities.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByuserNumber(String userNumber);
}

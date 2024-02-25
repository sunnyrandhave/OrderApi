package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}

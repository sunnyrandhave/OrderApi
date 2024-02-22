package com.OrderApi.orderAPI.Repositories;

import com.OrderApi.orderAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

}

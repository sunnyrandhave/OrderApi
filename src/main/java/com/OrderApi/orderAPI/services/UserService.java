package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public User createUser(User user){
            userRepository.save(user);
            return user;

    }

    public String getUserById(int id){
        Boolean a = userRepository.existsById(id);
        if(a){
            return userRepository.getById(id).toString();

        }else{
            return "Not Found";
        }
    }

    public String deleteUserByID(int id){
        if(userRepository.existsById(id)){
            userRepository.delete(userRepository.getById(id));
            return "user deleted";
        }else{
            return "User Not Found";
        }
    }


}

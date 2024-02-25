package com.OrderApi.orderAPI.services;

import com.OrderApi.orderAPI.Repositories.UserRepository;
import com.OrderApi.orderAPI.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private User user;



    public ResponseEntity<String> createUser(User user){
        try {
            Boolean flag =  userRepository.existsById(user.getUserId());
            if(user.getUserPhoneNumber().length()==10 && !flag && userRepository.existsById(user.getUserId())){
                userRepository.save(user);
                return ResponseEntity.accepted().body("user Created");
            }else{
                return ResponseEntity.internalServerError().body("User Already exists or Not Valid");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}

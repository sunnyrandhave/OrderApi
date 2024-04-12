package com.OrderApi.orderAPI.Services;

import com.OrderApi.orderAPI.Entities.User;
import com.OrderApi.orderAPI.Exceptions.InvalidPhoneNumberException;
import com.OrderApi.orderAPI.Exceptions.UserNumberAlreadyExistsException;
//import com.OrderApi.orderAPI.Repositories.ProductRepository;
import com.OrderApi.orderAPI.Repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) throws UserNumberAlreadyExistsException, InvalidPhoneNumberException {
        Optional<User> userOptional = userRepository.findByuserNumber(user.getUserNumber());
        if(userOptional.isPresent()){
            throw new UserNumberAlreadyExistsException("Number Provided is already mapped with other user");
        }if(user.getUserNumber().length()!=10 && !StringUtils.isNumeric(user.getUserNumber())){
            throw new InvalidPhoneNumberException("Please provide Valid Number");
        }else{
            userRepository.save(user);
            return user.getUserName()+ " Registered SuccessFully";
        }
    }



}

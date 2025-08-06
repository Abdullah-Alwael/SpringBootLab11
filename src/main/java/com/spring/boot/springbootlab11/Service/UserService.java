package com.spring.boot.springbootlab11.Service;

import com.spring.boot.springbootlab11.Api.ApiException;
import com.spring.boot.springbootlab11.Model.User;
import com.spring.boot.springbootlab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user){
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }

    public List<User> getAlUsers(){
        return userRepository.findAll();
    }

    public void updateUser(Integer user_id, User user){
        User oldUser = userRepository.findUserByUserId(user_id);

        if (oldUser == null){
            throw new ApiException("Error user not found");
        }

        oldUser.setUserName(user.getUserName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser);

    }

    public void deleteUser(Integer user_id){
        User oldUser = userRepository.findUserByUserId(user_id);

        if (oldUser == null){
            throw new ApiException("Error user not found");
        }

        userRepository.delete(oldUser);

    }

    // Extra:
    // check username and password

    public void checkUserNameAndPassword(String userName, String password){
        User checkUser = userRepository.checkUserNameAndPassword(userName, password);

        if (checkUser == null){
            throw new ApiException("Error user not found");
        }

        // nothing else to check
    }

    // get all users registered between a range of date
    public List<User> getUsersRegisteredBetween(LocalDate registrationDateAfter, LocalDate registrationDateBefore){
        return userRepository.findUserByRegistrationDateBetween(registrationDateAfter, registrationDateBefore);
    }
}

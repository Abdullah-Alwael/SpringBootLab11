package com.spring.boot.springbootlab11.Repository;

import com.spring.boot.springbootlab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUserId(Integer userId);

    @Query("select u from User u where u.userName=?1 and u.password=?2")
    User checkUserNameAndPassword(String userName, String password);

    List<User> findUserByRegistrationDateBetween(LocalDate registrationDateAfter, LocalDate registrationDateBefore);
}

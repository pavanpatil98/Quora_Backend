package com.example.quora.repositories;

import com.example.quora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Driver;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndEmailId(String username, String emailId);

    //List<User> findAllByUserNameAndEmailId(String userName,String emailId);

    //@Query(nativeQuery = true,value="SELECT * FROM User WHERE username = :userName AND email_id = :emailId")
    //List<User> rawfindByIdAndLicenseNumber(@Param("userName")String userName, @Param("emailId") String emailId);
}

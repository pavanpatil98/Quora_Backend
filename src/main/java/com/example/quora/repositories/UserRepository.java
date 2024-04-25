package com.example.quora.repositories;

import com.example.quora.model.Question;
import com.example.quora.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndEmailId(String username, String emailId);

    List<User> findAllByIdIn(List<Long> userId);

    //List<User> findAllByUserNameAndEmailId(String userName,String emailId);

    //@Query(nativeQuery = true,value="SELECT * FROM User WHERE username = :userName AND email_id = :emailId")
    //List<User> rawfindByIdAndLicenseNumber(@Param("userName")String userName, @Param("emailId") String emailId);
}

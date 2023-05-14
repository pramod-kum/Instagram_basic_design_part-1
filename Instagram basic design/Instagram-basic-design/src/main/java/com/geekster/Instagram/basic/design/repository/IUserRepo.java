package com.geekster.Instagram.basic.design.repository;

import com.geekster.Instagram.basic.design.model.AuthenticationToken;
import com.geekster.Instagram.basic.design.model.User;
import org.apache.catalina.realm.JNDIRealm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User , Long> {

    User findFirstByUserEmail(String email);
    @Modifying
    @Query(value = "update user set user_first_name = :userFirstName , user_Last_Name = :userLastName , user_Age = :userAge , user_Email = :userEmail , user_Phone_Number = :userPhoneNumber where user_Password = :encryptedPassword" , nativeQuery = true)
    void updateUser(String userFirstName, String userLastName, Integer userAge, String userEmail, String encryptedPassword, String userPhoneNumber);


}

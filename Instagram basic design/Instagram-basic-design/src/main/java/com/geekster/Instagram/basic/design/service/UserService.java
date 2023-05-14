package com.geekster.Instagram.basic.design.service;

import com.geekster.Instagram.basic.design.dto.SignInInput;
import com.geekster.Instagram.basic.design.dto.SignInOutput;
import com.geekster.Instagram.basic.design.dto.SignUpInput;
import com.geekster.Instagram.basic.design.dto.SignUpOutput;
import com.geekster.Instagram.basic.design.model.AuthenticationToken;
import com.geekster.Instagram.basic.design.model.User;
import com.geekster.Instagram.basic.design.repository.IAuthenticationTokenRepo;
import com.geekster.Instagram.basic.design.repository.IUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
   private IUserRepo iUserRepo;

    @Autowired
    private IAuthenticationTokenRepo authenticationTokenRepo;

    public SignUpOutput signUp(SignUpInput signUpInput) {
        User userObj = iUserRepo.findFirstByUserEmail(signUpInput.getEmail());
        if (userObj != null){
            return new SignUpOutput("User already exist","Error!!");
        }
        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(signUpInput.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        User userObject = new User(signUpInput.getFirstName() , signUpInput.getLastName() , signUpInput.getAge() ,signUpInput.getEmail() , encryptedPassword ,signUpInput.getPhoneNumber());

        iUserRepo.save(userObject);

        AuthenticationToken authenticationTokenObj = new AuthenticationToken(userObject);

        authenticationTokenRepo.save(authenticationTokenObj);
        return new SignUpOutput("SignUn Successfully" , "SignIn Now!!");
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());
        byte[] encryptPassword = md5.digest();
        String encryptedPassword = new String(encryptPassword);
        return encryptedPassword;
    }

    public SignInOutput signIn(SignInInput signInInput) {

    try {
        User userObj = iUserRepo.findFirstByUserEmail(signInInput.getEmail());
        String token = authenticationTokenRepo.findFirstByUser(userObj).getToken();

        String encryptedPassword;
        try {
            encryptedPassword = encryptPassword(signInInput.getPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if ((userObj.getUserEmail()).equals(signInInput.getEmail()) && encryptedPassword.equals(userObj.getUserPassword())) {
            return new SignInOutput("SignIn Successfully", token);    //user_user_id
        } else {
            return new SignInOutput("Something Wrong!!", "Try again!!");    //user_user_id
        }

    }catch (Exception e){
        System.out.println(e);
        return new SignInOutput("Something Wrong!!", "Try again!!");
     }
    }
    @Transactional
    public String updateUser(User userObj) {

            User userObject = iUserRepo.findFirstByUserEmail(userObj.getUserEmail());


        if (userObject == null){
            return "Something Wrong!! Try again!! may be null!!";
        }

            String encryptedPassword;
            try {
                encryptedPassword = encryptPassword(userObj.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            if (userObject.getUserEmail().equals(userObj.getUserEmail()) && encryptedPassword.equals(userObject.getUserPassword())) {

                iUserRepo.updateUser( userObj.getUserFirstName(),userObj.getUserLastName(),userObj.getUserAge(),userObj.getUserEmail(),encryptedPassword,userObj.getUserPhoneNumber() );
                return "User Updated Successfull!! Sign In Now!!";
            } else {
                return "Something Wrong!! Try again!!";
            }

    }

    public boolean verify(String emailId, String token) {
        User userObj = iUserRepo.findFirstByUserEmail(emailId);
        if(userObj == null){
            return false;
        }
        AuthenticationToken authenticationObj = authenticationTokenRepo.findFirstByUser(userObj);

        if(userObj.getUserEmail().equals(emailId)  && authenticationObj.getToken().equals(token)){
            return true;
        }else {
            return false;
        }

    }
}

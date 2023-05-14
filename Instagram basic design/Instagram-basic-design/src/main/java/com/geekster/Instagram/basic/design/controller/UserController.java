package com.geekster.Instagram.basic.design.controller;

import com.geekster.Instagram.basic.design.dto.*;
import com.geekster.Instagram.basic.design.model.Post;
import com.geekster.Instagram.basic.design.model.User;
import com.geekster.Instagram.basic.design.service.PostService;
import com.geekster.Instagram.basic.design.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postServiceObj;
    @PostMapping("/signUp")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }
    @PostMapping("/signIn")
    public SignInOutput signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User userObj){
        return userService.updateUser(userObj);
    }

    //Post
    @PostMapping("savePost/{emailId}/{token}")
    public String post(@RequestBody PostInput postInput, @PathVariable String emailId , @PathVariable String token){
       // return postServiceObj.uplodePost(postInput,emailId,token);
         String message;
        if(userService.verify(emailId,token)){
            message = postServiceObj.uploadNow(postInput , emailId);
        }else {
            message = "Something Wrong!! Try again!!";
        }
        return message;
    }

    @GetMapping("getPost/{postId}/{emailId}/{token}")
    public Optional<Post> getPost(@PathVariable String emailId , @PathVariable String token , @PathVariable Integer postId){



        if(userService.verify(emailId,token)){

            return postServiceObj.getAllPost(postId);

        }else {
            throw new IllegalStateException("Post not Found Error!!");
        }

    }
}

package com.geekster.Instagram.basic.design.service;

import com.geekster.Instagram.basic.design.dto.PostInput;
import com.geekster.Instagram.basic.design.model.Post;
import com.geekster.Instagram.basic.design.model.User;
import com.geekster.Instagram.basic.design.repository.IPostRepo;
import com.geekster.Instagram.basic.design.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private IPostRepo iPostRepo;
    @Autowired
    private IUserRepo UserRepo;
    @Autowired
    private UserService userServiceObj;

    public String uploadNow(PostInput postInput, String emailId) {
        User userObj = UserRepo.findFirstByUserEmail(emailId);
        Post postObj = new Post(postInput.getPostData(),userObj);
        iPostRepo.save(postObj);

        return "Post Uploaded Successfully!!";
    }

    public Optional<Post> getAllPost(Integer postId) {

        return iPostRepo.findById(postId);
    }

}

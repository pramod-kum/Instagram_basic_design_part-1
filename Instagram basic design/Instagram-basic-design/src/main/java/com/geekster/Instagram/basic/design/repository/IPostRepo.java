package com.geekster.Instagram.basic.design.repository;

import com.geekster.Instagram.basic.design.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepo extends JpaRepository<Post , Integer> {
}

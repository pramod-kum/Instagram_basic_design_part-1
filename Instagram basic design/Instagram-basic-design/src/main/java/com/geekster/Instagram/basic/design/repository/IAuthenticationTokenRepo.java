package com.geekster.Instagram.basic.design.repository;

import com.geekster.Instagram.basic.design.model.AuthenticationToken;
import com.geekster.Instagram.basic.design.model.User;
import org.apache.el.parser.ELParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationTokenRepo extends JpaRepository<AuthenticationToken ,Long> {


    AuthenticationToken findFirstByUser(User userObj);
}

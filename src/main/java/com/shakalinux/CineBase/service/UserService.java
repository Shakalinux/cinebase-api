package com.shakalinux.CineBase.service;

import com.shakalinux.CineBase.model.User;
import com.shakalinux.CineBase.repository.UserRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepostitory userRepostitory;

    public User findByEmail(String email){
        return userRepostitory.findByEmail(email).orElseThrow(()
            -> new RuntimeException("Usuario nao encontrado " +email));
    }

    public Optional<User> findUser(String email){
        return userRepostitory.findByEmail(email);
    }


    public List<User> listUsers(){
        return userRepostitory.findAll();
    }







    public User save(User user){
        return userRepostitory.save(user);
    }


}

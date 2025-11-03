package com.shakalinux.CineBase.service;

import com.shakalinux.CineBase.model.Role;
import com.shakalinux.CineBase.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRole(String name){
        return roleRepository.findByName(name);
    }
}

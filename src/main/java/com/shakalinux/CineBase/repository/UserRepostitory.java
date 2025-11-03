package com.shakalinux.CineBase.repository;

import com.shakalinux.CineBase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepostitory extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

}

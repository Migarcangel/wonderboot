package com.project.wonderboot.repository;

import com.project.wonderboot.models.user.WonderUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WonderUserRepository extends org.springframework.data.repository.Repository<WonderUser,Long> {
    List<WonderUser> findAll();
    WonderUser save(WonderUser wonderUser);
    Optional<WonderUser> findByProfileUsername(final String username);
}

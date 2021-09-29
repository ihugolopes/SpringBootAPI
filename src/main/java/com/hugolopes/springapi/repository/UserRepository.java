package com.hugolopes.springapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugolopes.springapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}

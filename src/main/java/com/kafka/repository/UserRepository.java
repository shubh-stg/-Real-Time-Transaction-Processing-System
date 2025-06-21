package com.kafka.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kafka.entity.User;

import jakarta.persistence.LockModeType;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("Select u from User u where u.id= :id")
	Optional<User> findByIdForUpdate(@Param("id") Long id);
	
	Boolean existsByUsername(String username);
	
     Optional<User> findByUsername(String username);

}

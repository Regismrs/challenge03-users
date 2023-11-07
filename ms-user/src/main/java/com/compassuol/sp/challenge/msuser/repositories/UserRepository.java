package com.compassuol.sp.challenge.msuser.repositories;

import com.compassuol.sp.challenge.msuser.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

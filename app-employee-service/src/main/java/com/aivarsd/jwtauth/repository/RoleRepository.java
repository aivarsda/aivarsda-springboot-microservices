package com.aivarsd.jwtauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aivarsd.jwtauth.entities.Role;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String roleName);
}
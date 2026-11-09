
package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDetailsRepo extends JpaRepository<LoginDetails, Long> {

    Optional<LoginDetails> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);
}

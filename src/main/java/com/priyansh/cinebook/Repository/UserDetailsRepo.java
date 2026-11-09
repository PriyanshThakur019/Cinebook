
package com.priyansh.cinebook.Repository;

import com.priyansh.cinebook.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByMobile(String mobile);

    Optional<UserDetails> findByLoginDetails_Username(String username);

    boolean existsByMobile(String mobile);

    boolean existsByLoginDetails_Username(String username);
}

package com.ggi.repository;

import com.ggi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    //Page<Component> findByDiagramId(Long id, Pageable pageable);
    //Optional<Component> findByIdAndDiagramId(Long id, Long diagramId);
   //Page<Form> findByFormManagerId(Long id, Pageable pageable);
    //Optional<Form> findByIdAndFormManagerId(Long id, Long formManagerId);

}
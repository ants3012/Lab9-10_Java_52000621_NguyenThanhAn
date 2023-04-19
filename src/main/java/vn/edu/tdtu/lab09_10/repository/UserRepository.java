package vn.edu.tdtu.lab09_10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.tdtu.lab09_10.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
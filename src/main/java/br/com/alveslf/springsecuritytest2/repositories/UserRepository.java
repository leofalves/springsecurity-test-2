package br.com.alveslf.springsecuritytest2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alveslf.springsecuritytest2.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}

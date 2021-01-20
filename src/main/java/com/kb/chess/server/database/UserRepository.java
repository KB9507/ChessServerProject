package com.kb.chess.server.database;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
}

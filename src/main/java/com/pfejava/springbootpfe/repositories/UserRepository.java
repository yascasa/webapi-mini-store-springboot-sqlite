package com.pfejava.springbootpfe.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfejava.springbootpfe.dao.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
	@Query("SELECT u FROM AppUser u WHERE lower(u.userName) LIKE lower(:userName) AND u.userPassword = :userPassword")
	public AppUser getUserByUserName(@Param("userName") String userName, @Param("userPassword") String userPassword);

	public AppUser findByUserName(String userName);
}

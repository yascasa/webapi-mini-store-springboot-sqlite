package com.pfejava.springbootpfe.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfejava.springbootpfe.dao.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


}

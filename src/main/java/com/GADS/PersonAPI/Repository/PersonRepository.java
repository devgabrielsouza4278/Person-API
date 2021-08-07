package com.GADS.PersonAPI.Repository;

import com.GADS.PersonAPI.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

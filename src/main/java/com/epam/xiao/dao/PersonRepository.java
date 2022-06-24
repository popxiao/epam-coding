package com.epam.xiao.dao;

import com.epam.xiao.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity,String> {
}

package com.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springsecurity.bean.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}

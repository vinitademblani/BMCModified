package com.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.bean.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

}

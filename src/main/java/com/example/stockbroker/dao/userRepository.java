package com.example.stockbroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<users,Integer> {

   List<users> findUsersByEmail(String email);

}

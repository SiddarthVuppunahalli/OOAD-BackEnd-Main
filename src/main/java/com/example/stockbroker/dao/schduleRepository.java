package com.example.stockbroker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface schduleRepository extends JpaRepository<schedule,Integer> {
    List<schedule> findScheduleByEmailAndTicekrsymbol(String email, String tickersymbol);
}
